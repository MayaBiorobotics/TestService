package com.requestdesign.testingservice.repository.result;

import com.requestdesign.testingservice.dto.client.ClientTestResultDto;
import com.requestdesign.testingservice.entity.phrase.Code;
import com.requestdesign.testingservice.entity.result.QuestionAnswer;
import com.requestdesign.testingservice.entity.result.TaskAnswer;
import com.requestdesign.testingservice.entity.result.TestResult;
import com.requestdesign.testingservice.exceptions.result.QuestionAnswerNotFoundException;
import com.requestdesign.testingservice.exceptions.result.ResultNotFoundException;
import com.requestdesign.testingservice.exceptions.result.TaskAnswerNotFoundException;
import com.requestdesign.testingservice.rowmapper.result.QuestionAnswerRowMapper;
import com.requestdesign.testingservice.rowmapper.result.ResultRowMapper;
import com.requestdesign.testingservice.rowmapper.result.TaskAnswerRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class ResultRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public ResultRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Transactional
    public TestResult findResultById(Long id) throws ResultNotFoundException {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        String selectResultQuery = "select * from test_result where id = :id";
        parameterSource.addValue("id", id);
        String selectQuestionAnswerQuery = "select * from question_answer as qa join test_result as tr on qa.test_result_id = tr.id where tr.id = :id";
        String selectTaskAnswerQuery = "select * from task_answer as qa join test_result as tr on qa.test_result_id = tr.id where tr.id = :id";
        Optional<TestResult> testResult = namedParameterJdbcTemplate.query(selectResultQuery, parameterSource, new ResultRowMapper()).stream().findFirst();
        if(testResult.isEmpty()) {
            throw new ResultNotFoundException("Результата с таким id нет");
        }

        List<QuestionAnswer> questionAnswer = namedParameterJdbcTemplate.query(selectQuestionAnswerQuery, parameterSource, new QuestionAnswerRowMapper()).stream().toList();
        List<TaskAnswer> taskAnswer = namedParameterJdbcTemplate.query(selectTaskAnswerQuery, parameterSource, new TaskAnswerRowMapper()).stream().toList();

        testResult.get().setQuestionAnswers(questionAnswer);
        testResult.get().setTaskAnswers(taskAnswer);

        return testResult.get();
    }

    @Transactional
    public Long createDefaultResult(Long testId, Long respondentId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        String createResultQuery = "insert into test_result(test_id, respondent_id) values(:test_id, :respondent_id)";
        parameterSource.addValue("test_id", testId);
        parameterSource.addValue("respondent_id", respondentId);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(createResultQuery, parameterSource, keyHolder);
        return (Long)keyHolder.getKeys().get("id");
    }

    @Transactional
    public QuestionAnswer findQuestionAnswerFromResultById(Long resultId, Long questionId) throws QuestionAnswerNotFoundException {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        String selectQuestionAnswerQuery = "select * from question_answer as qa where qa.test_result_id = :result_id and qa.question_id = :question_id";
        parameterSource.addValue("result_id", resultId);
        parameterSource.addValue("question_id", questionId);
        Optional<QuestionAnswer> questionAnswer = namedParameterJdbcTemplate.query(selectQuestionAnswerQuery, parameterSource, new QuestionAnswerRowMapper()).stream().findFirst();
        if(questionAnswer.isEmpty()) {
            throw new QuestionAnswerNotFoundException("Ответа на данный вопрос в таком результате нет");
        }

        return questionAnswer.get();
    }

    public TaskAnswer findTaskFromResultById(Long resultId, Long taskId) throws TaskAnswerNotFoundException {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        String selectQuestionAnswerQuery = "select * from task_answer as qa where qa.test_result_id = :result_id and qa.task_id = :task_id";
        parameterSource.addValue("result_id", resultId);
        parameterSource.addValue("task_id", taskId);
        Optional<TaskAnswer> questionAnswer = namedParameterJdbcTemplate.query(selectQuestionAnswerQuery, parameterSource, new TaskAnswerRowMapper()).stream().findFirst();
        if(questionAnswer.isEmpty()) {
            throw new TaskAnswerNotFoundException("Ответа на данный вопрос в таком результате нет");
        }

        return questionAnswer.get();
    }

    public void saveResult(Long resultId, ClientTestResultDto result) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        String saveResultQuery = "update test_result set creation_time = :date_time where id = :resultId";
        parameterSource.addValue("date_time", LocalDateTime.now());
        parameterSource.addValue("resultId", resultId);
        namedParameterJdbcTemplate.update(saveResultQuery, parameterSource);
        String saveQuestionAnswerQuery = "insert into question_answer(test_result_id, question_id, variant_id) values(:resultId, :question_id, :variant_id)";
        String saveTaskQuery = "insert into task_answer(test_result_id, task_id, text) values(:resultId, :task_id, :text)";
        for(var questionAnswer: result.getQuestionAnswers()) {
            parameterSource.addValue("question_id", questionAnswer.getQuestionId());
            parameterSource.addValue("variant_id", questionAnswer.getVariantId());
            namedParameterJdbcTemplate.update(saveQuestionAnswerQuery, parameterSource);
        }

        for(var taskAnswer: result.getTaskAnswers()) {
            parameterSource.addValue("task_id", taskAnswer.getTaskId());
            parameterSource.addValue("text", taskAnswer.getText());
            namedParameterJdbcTemplate.update(saveTaskQuery, parameterSource);
        }
    }
}
