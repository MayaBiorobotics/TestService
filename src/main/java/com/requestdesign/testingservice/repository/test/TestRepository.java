package com.requestdesign.testingservice.repository.test;

import com.requestdesign.testingservice.dto.test.TaskBlockNumberDto;
import com.requestdesign.testingservice.dto.test.TestCreateDto;
import com.requestdesign.testingservice.dto.test.TestManuallyCreateDto;
import com.requestdesign.testingservice.dto.test.phrase.PhraseToTestDto;
import com.requestdesign.testingservice.dto.test.question.QuestionBlockCreateDto;
import com.requestdesign.testingservice.dto.test.task.TaskBlockCreateDto;
import com.requestdesign.testingservice.dto.test.task.TaskDto;
import com.requestdesign.testingservice.entity.phrase.TestPhrase;
import com.requestdesign.testingservice.entity.test.Task;
import com.requestdesign.testingservice.entity.test.Test;
import com.requestdesign.testingservice.exceptions.phrase.PhraseNotFoundException;
import com.requestdesign.testingservice.exceptions.test.TaskNotFoundException;
import com.requestdesign.testingservice.exceptions.test.TestNotFoundException;
import com.requestdesign.testingservice.rowmapper.phrase.TestPhraseRowMapper;
import com.requestdesign.testingservice.rowmapper.test.SimpleTestRowMapper;
import com.requestdesign.testingservice.rowmapper.test.TestRowMapper;
import com.requestdesign.testingservice.rowmapper.test.task.TaskRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Repository
public class TestRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public TestRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Test findById(Long id) throws TestNotFoundException {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        String selectByIdQuery = "select  " +
                "test.title as title, " +
                "test.region as region, " +
                "test.url as url, " +
                "qb.id as question_block_id, " +
                "qb.title as question_block_title, " +
                "q.id as question_id, " +
                "q.text as question_text, " +
                "q.picture as question_picture " +
                "from test as test  " +
                "full join test_to_question_block as ttqb " +
                "on ttqb.test_id = test.id " +
                "full join question_block as qb " +
                "on qb.id = ttqb.question_block_id " +
                "full join question_block_to_question as qbtq " +
                "on qbtq.question_block_id = qb.id " +
                "full join question as q " +
                "on q.id = qbtq.question_id";
        parameterSource.addValue("id", id);
        Optional<Test> test = namedParameterJdbcTemplate.query(selectByIdQuery, parameterSource, new TestRowMapper()).stream().findFirst();
        if(test.isEmpty()) {
            throw new TestNotFoundException("Тест с таким id не существует");
        } else {
            return test.get();
        }
    }

    public List<Test> findAllTests() {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        String selectAllQuery = "select * from test";
        return new ArrayList<>(namedParameterJdbcTemplate.query(selectAllQuery, mapSqlParameterSource, new SimpleTestRowMapper()).stream().toList());
    }

    @Transactional
    public Long saveTest(TestCreateDto test) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("title", test.getTitle());
        mapSqlParameterSource.addValue("url", test.getUrl());
        mapSqlParameterSource.addValue("region", test.getRegion());
        mapSqlParameterSource.addValue("comment", test.getComment());
        String createTestQuery = "insert into test(title, url, region, comment) " +
                "values(:title, :url, :region, :comment);";
        KeyHolder holder = new GeneratedKeyHolder();
        String addQuestionBlockToTest = "insert into test_to_question_block(test_id, question_block_id)" +
                " values(:test_id, :question_block_id)";
        namedParameterJdbcTemplate.update(createTestQuery, mapSqlParameterSource, holder);
        Long id = (Long) holder.getKeys().get("id");
        mapSqlParameterSource.addValue("test_id", id);
        for(var question_block: test.getQuestionBlockIds()) {
            mapSqlParameterSource.addValue("question_block_id", question_block);
            namedParameterJdbcTemplate.update(addQuestionBlockToTest, mapSqlParameterSource, holder);
        }
        String addTaskBlockToTest = "insert into test_to_block(test_id, task_block_id, number)" +
                " values(:test_id, :task_block_id, :number)";
        for(var task_block: test.getTaskBlockNumberDtos()) {
            mapSqlParameterSource.addValue("task_block_id", task_block.getTaskBlockId());
            mapSqlParameterSource.addValue("number", task_block.getInteger());
            namedParameterJdbcTemplate.update(addTaskBlockToTest, mapSqlParameterSource, holder);
        }
        return id;
    }

    @Transactional
    public Long createTaskBlock(TaskBlockCreateDto taskBlock) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        String createQuestionQuery = "insert into task_block(title) "+
                "values(:title)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(createQuestionQuery, mapSqlParameterSource, keyHolder);
        Long id = (Long)keyHolder.getKeys().get("id");
        String addTaskToBlockQuery = "insert into task_block_to_task(task_block_id, task_id) " +
                "values(:task_block_id, :task_id)";
        mapSqlParameterSource.addValue("task_block_id", id);
        for(var task: taskBlock.getTaskIds()) {
            mapSqlParameterSource.addValue("task_id", task);
            namedParameterJdbcTemplate.update(addTaskToBlockQuery, mapSqlParameterSource);
        }

        return id;
    }

    @Transactional
    public Long createQuestionBlock(QuestionBlockCreateDto questionBlockDto) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        String createQuestionQuery = "insert into question_block(title) "+
                "values(:title)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(createQuestionQuery, mapSqlParameterSource, keyHolder);
        Long id = (Long)keyHolder.getKeys().get("id");
        String addTaskToBlockQuery = "insert into question_block_to_question(question_block_id, question_id) " +
                "values(:task_block_id, :task_id)";
        mapSqlParameterSource.addValue("task_block_id", id);
        for(var question: questionBlockDto.getQuestionIds()) {
            mapSqlParameterSource.addValue("question_id", question);
            namedParameterJdbcTemplate.update(addTaskToBlockQuery, mapSqlParameterSource);
        }

        return id;
    }

    @Transactional
    public Long createTestManually(TestManuallyCreateDto test) {
        //TODO
        return null;
    }

    public void editTestById(Long id) {

    }

    public void copyQuestionById(Long id) {

    }

    public void editQuestionTextById(Long id) {

    }

    public void addQuestionBlockToTestById(Long test, Long block) {

    }

    public void addTaskBlockToTestById(Long test, TaskBlockNumberDto taskBlockNumber) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        String addTaskBlockQuery = "insert into test_to_block(test_id, task_block_id, number)" +
                "values(:test_id, :task_block_id, :number)";
        parameterSource.addValue("test_id", test);
        parameterSource.addValue("task_block_id", taskBlockNumber.getTaskBlockId());
        parameterSource.addValue("number", taskBlockNumber.getInteger());
        namedParameterJdbcTemplate.update(addTaskBlockQuery, parameterSource);
    }

    public void addTaskToTaskBlock(Long task, Long taskBlock) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        String addTaskToBlock = "insert into task_block_to_task(task_block_id, task_id) "+
                "values(:task_block, :task_id)";
        parameterSource.addValue("task_block", taskBlock);
        parameterSource.addValue("task_id", task);
        namedParameterJdbcTemplate.update(addTaskToBlock, parameterSource);
    }

    public TestPhrase findPhraseFromTestById(Long testId, Long phraseId) throws PhraseNotFoundException {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        String findPhraseFromTest = "select * from test_phrase as tp where tp.test_id = :test_id and tp.phrase_id = :phrase_id";
        parameterSource.addValue("test_id", testId);
        parameterSource.addValue("phrase_id", phraseId);
        Optional<TestPhrase> testPhrase = namedParameterJdbcTemplate.query(findPhraseFromTest, parameterSource, new TestPhraseRowMapper()).stream().findFirst();
        if(testPhrase.isEmpty()) {
            throw new PhraseNotFoundException("Фараза с таким id не указана в тесте");
        } else {
            return testPhrase.get();
        }
    }

    public void addPhraseToTest(Long testId, PhraseToTestDto phraseAddToTestDto) {
        try {
            MapSqlParameterSource parameterSource = new MapSqlParameterSource();
            String addPhraseQuery = "insert into test_phrase(phrase_id, intensivity, test_id) "+
                    "values(:phrase_id, :intensivity, :test_id)";
            parameterSource.addValue("phrase_id", phraseAddToTestDto.getPhraseId());
            parameterSource.addValue("intensivity", phraseAddToTestDto.getIntensivity());
            parameterSource.addValue("test_id", testId);
            namedParameterJdbcTemplate.update(addPhraseQuery, parameterSource);
        } catch(Exception e) {
            System.out.println("Hello world");
        }
    }

    public List<TestPhrase> findAllTestPhrasesFromTest(Long testId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        String selectAllQuery = "select * from test_phrase as tp where tp.test_id = :id";
        parameterSource.addValue("id", testId);
        List<TestPhrase> testPhrases = namedParameterJdbcTemplate.query(selectAllQuery, parameterSource, new TestPhraseRowMapper()).stream().toList();
        return testPhrases;
    }

    public void editPhraseInTest(Long testId, PhraseToTestDto phraseToTest) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        String editPhraseQuery = "update test_phrase set intensivity = :intensivity where test_id = :test_id and phrase_id = :phrase_id";
        parameterSource.addValue("test_id", testId);
        parameterSource.addValue("phrase_id", phraseToTest.getPhraseId());
        parameterSource.addValue("intensivity", phraseToTest.getIntensivity());
        namedParameterJdbcTemplate.update(editPhraseQuery, parameterSource);
    }
}
