package com.requestdesign.testingservice.repository.phrase;

import com.requestdesign.testingservice.dto.phrase.PhraseDto;
import com.requestdesign.testingservice.entity.phrase.DictionaryPhrase;
import com.requestdesign.testingservice.exceptions.phrase.DictionaryPhraseNotFoundException;
import com.requestdesign.testingservice.rowmapper.phrase.DictionaryPhraseRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PhraseRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public PhraseRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void addPhraseToDictionary(PhraseDto phraseDto) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        String addPhraseQuery = "insert into phrase_dictionary(phrase, group_id) values(:phrase, :group_id)";
        parameterSource.addValue("phrase", phraseDto.getPhrase());
        parameterSource.addValue("group_id", phraseDto.getGroupId());
        namedParameterJdbcTemplate.update(addPhraseQuery, parameterSource);
    }

    public List<DictionaryPhrase> findAllPhrases() {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        String selectAllFromDictionaryQuery = "select * from phrase_dictionary";
        List<DictionaryPhrase> phrases = namedParameterJdbcTemplate.query(selectAllFromDictionaryQuery, parameterSource, new DictionaryPhraseRowMapper()).stream().toList();
        return phrases;
    }

    public void editPhraseInDictionaryById(Long phraseId, PhraseDto phraseDto) {
        List<String> operators = new ArrayList<>();
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        String updateQuery = "update phrase_dictionary set ";
        if(phraseDto.getPhrase() != null) {
            operators.add("phrase = :phrase");
            parameterSource.addValue("phrase", phraseDto.getPhrase());
        }

        if(phraseDto.getGroupId() != null) {
            operators.add("group_id = :group_id");
            parameterSource.addValue("group_id", phraseDto.getGroupId());
        }

        for(String operator: operators) {
            updateQuery += " , ";
            updateQuery += operator;
        }

        namedParameterJdbcTemplate.update(updateQuery, parameterSource);
    }

    public DictionaryPhrase findPhraseById(Long phraseId) throws DictionaryPhraseNotFoundException {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        String selectQuery = "select * from phrase_dictionary where id = :id";
        parameterSource.addValue("id", phraseId);
        Optional<DictionaryPhrase> phrase = namedParameterJdbcTemplate.query(selectQuery, parameterSource, new DictionaryPhraseRowMapper()).stream().findFirst();
        if(phrase.isEmpty()) {
            throw new DictionaryPhraseNotFoundException("Фраза в словаре по такому id не найдена");
        } else {
            return phrase.get();
        }
    }
}
