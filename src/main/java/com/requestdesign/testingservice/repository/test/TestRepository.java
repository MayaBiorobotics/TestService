package com.requestdesign.testingservice.repository.test;

import com.requestdesign.testingservice.dto.test.TestCreateDto;
import com.requestdesign.testingservice.dto.test.TestManuallyCreateDto;
import com.requestdesign.testingservice.dto.test.question.QuestionBlockCreateDto;
import com.requestdesign.testingservice.dto.test.question.QuestionCreateDto;
import com.requestdesign.testingservice.dto.test.question.QuestionVariantAddDto;
import com.requestdesign.testingservice.dto.test.task.TaskBlockCreateDto;
import com.requestdesign.testingservice.dto.test.task.TaskCreateDto;
import com.requestdesign.testingservice.entity.test.Question;
import com.requestdesign.testingservice.entity.test.QuestionVariant;
import com.requestdesign.testingservice.entity.test.Task;
import com.requestdesign.testingservice.entity.test.Test;
import com.requestdesign.testingservice.exceptions.test.QuestionNotFoundException;
import com.requestdesign.testingservice.exceptions.test.TaskNotFoundException;
import com.requestdesign.testingservice.exceptions.test.TestNotFoundException;
import com.requestdesign.testingservice.rowmapper.test.SimpleTestRowMapper;
import com.requestdesign.testingservice.rowmapper.test.TestRowMapper;
import com.requestdesign.testingservice.rowmapper.test.question.QuestionRowMapper;
import com.requestdesign.testingservice.rowmapper.test.question.QuestionVariantRowMapper;
import com.requestdesign.testingservice.rowmapper.test.task.TaskRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.util.*;
import java.util.stream.Collectors;

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

    public Set<Task> findTasksByTestId(Long id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        String selectByIdQuery = "";
        mapSqlParameterSource.addValue("id", id);
        return new HashSet<>(namedParameterJdbcTemplate.query(selectByIdQuery, mapSqlParameterSource, new TaskRowMapper()));
    }

    public Task findTaskById(Long id) throws TaskNotFoundException {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        String selectByIdQuery = "";
        mapSqlParameterSource.addValue("id", id);
        Optional<Task> task = namedParameterJdbcTemplate.query(selectByIdQuery, mapSqlParameterSource, new TaskRowMapper()).stream().findFirst();
        if(task.isPresent()) {
            return task.get();
        } else {
            throw new TaskNotFoundException("Задания с таким id нет");
        }
    }

    public List<Test> findAllTests() {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        String selectAllQuery = "select * from test";
        return new ArrayList<>(namedParameterJdbcTemplate.query(selectAllQuery, mapSqlParameterSource, new SimpleTestRowMapper()).stream().toList());
    }

    public List<Task> findAllTasks() {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        String selectAllQuery = "select * from task";
        return new ArrayList<>(namedParameterJdbcTemplate.query(selectAllQuery, mapSqlParameterSource, new TaskRowMapper()).stream().toList());
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
    public Long createTask(TaskCreateDto task) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        String createTaskQuery = "insert into task(text) " +
                "values(:text)";
        mapSqlParameterSource.addValue("text", task.getText());
        KeyHolder holder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(createTaskQuery, mapSqlParameterSource, holder);
        Long id = (Long)holder.getKeys().get("id");
        return id;
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
    }

    public void editTestById(Long id) {

    }

    public void copyQuestionById(Long id) {

    }

    public void editQuestionTextById(Long id) {

    }

    public void editTaskTextById(Long id) {}

    public void addQuestionBlockToTestById(Long test, Long block) {

    }

    public void addTaskBlockToTestById(Long test, Long block) {

    }

    public void addTaskToTaskBlock(Long task, Long taskBlock) {

    }
}
