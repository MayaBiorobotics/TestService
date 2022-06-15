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
import java.util.stream.Collectors;

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
                "q.id as id, " +
                "q.text as text, " +
                "q.picture as picture, " +
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

    public List<QuestionVariant> findQuestionVariantsByQuestionId(Long id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        String selectByIdQuery = "select * from question_variant as qv join question as q on q.id = qv.question_id where q.id = :id";
        mapSqlParameterSource.addValue("id", id);
        return namedParameterJdbcTemplate.query(selectByIdQuery, mapSqlParameterSource, new QuestionVariantRowMapper()).stream().toList();
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
    public void addVariantToQuestion(Long questionId, QuestionVariantAddDto questionVariant) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        String addVariantQuery = "insert into question_variant(question_id, text, picture) "+
                "values(:question_id, :text, :picture)";
        mapSqlParameterSource.addValue("question_id", questionId);
        mapSqlParameterSource.addValue("text", questionVariant.getText());
        mapSqlParameterSource.addValue("picture", questionVariant.getPicture());
        namedParameterJdbcTemplate.update(addVariantQuery, mapSqlParameterSource);
    }

    public List<Question> findAllQuestions() {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        String selectAllQuery = "select * from question";
        List<Question> questions = namedParameterJdbcTemplate.query(selectAllQuery, mapSqlParameterSource, new QuestionRowMapper()).stream().toList();
        for(var question: questions) {
            question.setQuestionVariants(findQuestionVariantsByQuestionId(question.getId()).stream().toList());
        }

        return questions;
    }
}
