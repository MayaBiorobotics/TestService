package com.requestdesign.testingservice.repository.question;

import com.requestdesign.testingservice.dto.test.question.QuestionCreateDto;
import com.requestdesign.testingservice.dto.test.question.QuestionVariantAddDto;
import com.requestdesign.testingservice.entity.test.Question;
import com.requestdesign.testingservice.entity.test.QuestionVariant;
import com.requestdesign.testingservice.exceptions.test.QuestionNotFoundException;
import com.requestdesign.testingservice.rowmapper.test.question.QuestionRowMapper;
import com.requestdesign.testingservice.rowmapper.test.question.QuestionVariantRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class QuestionRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public QuestionRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Question findQuestionById(Long id) throws QuestionNotFoundException {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        String selectByIdQuery = "select  " +
                "q.id as question_id, " +
                "q.text as question_text, " +
                "qv.id as question_variant_id, " +
                "qv.text as question_variant_text " +
                "from question as q " +
                "right join question_variant as qv on qv.question_id = q.id";
        parameterSource.addValue("id", id);
        Optional<Question> question = namedParameterJdbcTemplate.query(selectByIdQuery, parameterSource, new QuestionRowMapper()).stream().findFirst();
        if(question.isPresent()) {
            question.get().setQuestionVariants(findQuestionVariantsByQuestionId(id));
        } else {
            throw new QuestionNotFoundException("Вопрос с данным id не найден");
        }

        return question.get();
    }

    public Set<QuestionVariant> findQuestionVariantsByQuestionId(Long id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        String selectByIdQuery = "";
        mapSqlParameterSource.addValue("id", id);
        return new HashSet<>(namedParameterJdbcTemplate.query(selectByIdQuery, mapSqlParameterSource, new QuestionVariantRowMapper()));
    }

    @Transactional
    public Long createQuestion(QuestionCreateDto question) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        String createQuestionQuery = "insert into question(text, picture) "+
                "values(:text, :picture)";
        mapSqlParameterSource.addValue("text", question.getText());
        mapSqlParameterSource.addValue("picture", question.getPicture());
        KeyHolder holder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(createQuestionQuery, mapSqlParameterSource, holder);
        Long id = (Long)holder.getKeys().get("id");
        return id;
    }

    @Transactional
    public void addVariantToQuestion(QuestionVariantAddDto questionVariant) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        String addVariantQuery = "insert into question_variant(question_id, text, picture) "+
                "values(:question_id, :text, :picture)";
        namedParameterJdbcTemplate.update(addVariantQuery, mapSqlParameterSource);
    }

    public List<Question> findAllQuestions() {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        String selectAllQuery = "select * from question";
        List<Question> questions = namedParameterJdbcTemplate.query(selectAllQuery, mapSqlParameterSource, new QuestionRowMapper()).stream().toList();
        for(var question: questions) {
            question.setQuestionVariants(new HashSet<>(findQuestionVariantsByQuestionId(question.getId())));
        }

        return questions;
    }
}
