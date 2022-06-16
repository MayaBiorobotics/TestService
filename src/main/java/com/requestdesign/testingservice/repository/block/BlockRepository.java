package com.requestdesign.testingservice.repository.block;

import com.requestdesign.testingservice.dto.test.question.QuestionBlockCreateDto;
import com.requestdesign.testingservice.dto.test.task.TaskBlockCreateDto;
import com.requestdesign.testingservice.entity.test.*;
import com.requestdesign.testingservice.exceptions.block.TaskBlockNotFoundException;
import com.requestdesign.testingservice.repository.task.TaskRepository;
import com.requestdesign.testingservice.rowmapper.test.question.QuestionBlockRowMapper;
import com.requestdesign.testingservice.rowmapper.test.question.QuestionRowMapper;
import com.requestdesign.testingservice.rowmapper.test.question.QuestionVariantRowMapper;
import com.requestdesign.testingservice.rowmapper.test.task.TaskBlockRowMapper;
import com.requestdesign.testingservice.rowmapper.test.task.TaskRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Repository
public class BlockRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final TaskRepository taskRepository;

    @Autowired
    public BlockRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate, TaskRepository taskRepository) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.taskRepository = taskRepository;
    }

    @Transactional
    public QuestionBlock findQuestionBlockById(Long blockId) throws TaskBlockNotFoundException {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        String selectTaskBlock = "select * from question_block where id = :id";
        parameterSource.addValue("id", blockId);
        Optional<QuestionBlock> questionBlock = namedParameterJdbcTemplate.query(selectTaskBlock, parameterSource, new QuestionBlockRowMapper()).stream().findFirst();
        if(questionBlock.isEmpty()) {
            throw new TaskBlockNotFoundException("Блок с таким id не найден");
        }
        String selectTaskFromBlockQuery = "select * from question_block_to_question as qbtq join question as q on q.id = qbtq.question_id where qbtq.question_block_id = :id";
        List<Question> questions = namedParameterJdbcTemplate.query(selectTaskFromBlockQuery, parameterSource, new QuestionRowMapper()).stream().toList();
        for(var question: questions) {
            String selectVariantsQuery = "select * from question_variant where question_id = :id";
            parameterSource.addValue("id", question.getId());
            List<QuestionVariant> questionVariants = namedParameterJdbcTemplate.query(selectVariantsQuery, parameterSource, new QuestionVariantRowMapper()).stream().toList();
            question.setQuestionVariants(questionVariants);
        }
        questionBlock.get().setQuestions(questions);
        return questionBlock.get();
    }

    @Transactional
    public void editQuestionBlockById(Long blockId, QuestionBlockCreateDto questionBlockCreateDto) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        String editTaskBlockQuery = "";
        if(questionBlockCreateDto.getTitle() != null) {
            parameterSource.addValue("title", questionBlockCreateDto.getTitle());
            editTaskBlockQuery = "update task_block set title = :title";
            namedParameterJdbcTemplate.update(editTaskBlockQuery, parameterSource);
        }
        for(var task: questionBlockCreateDto.getQuestionIds()) {
            parameterSource.addValue("task_id", task);
            editTaskBlockQuery = "insert into question_block_to_question(question_block_id, question_id) VALUES (:block_id, :task_id)";
        }
    }

    @Transactional
    public TaskBlock findTaskBlockById(Long blockId) throws TaskBlockNotFoundException {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        String selectTaskBlock = "select * from test_to_block as ttb join task_block as tb on ttb.task_block_id = tb.id where tb.id = :id";
        parameterSource.addValue("id", blockId);
        Optional<TaskBlock> taskBlock = namedParameterJdbcTemplate.query(selectTaskBlock, parameterSource, new TaskBlockRowMapper()).stream().findFirst();
        if(taskBlock.isEmpty()) {
            throw new TaskBlockNotFoundException("Блок с таким id не найден");
        }
        String selectTaskFromBlockQuery = "select * from task_block_to_task as tbtt join task as t on t.id = tbtt.task_id where tbtt.task_block_id = :id";
        List<Task> tasks = namedParameterJdbcTemplate.query(selectTaskFromBlockQuery, parameterSource, new TaskRowMapper()).stream().toList();
        taskBlock.get().setTasks(new HashSet<>(tasks));
        return taskBlock.get();
    }

    @Transactional
    public void editTaskBlockById(Long blockId, TaskBlockCreateDto taskBlockCreateDto) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        String editTaskBlockQuery = "";
        if(taskBlockCreateDto.getTitle() != null) {
            parameterSource.addValue("title", taskBlockCreateDto.getTitle());
            editTaskBlockQuery = "update task_block set title = :title where id = :id";
            parameterSource.addValue("id", blockId);
            namedParameterJdbcTemplate.update(editTaskBlockQuery, parameterSource);
        }
        for(var task: taskBlockCreateDto.getTaskIds()) {
            parameterSource.addValue("task_id", task);
            editTaskBlockQuery = "insert into task_block_to_task(task_block_id, task_id) VALUES (:id, :task_id)";
            namedParameterJdbcTemplate.update(editTaskBlockQuery, parameterSource);
        }
    }

    @Transactional
    public List<QuestionBlock> findAllQuestionBlocks() throws TaskBlockNotFoundException {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        String selectQuery = "select * from question_block";
        List<QuestionBlock> questionBlocks = namedParameterJdbcTemplate.query(selectQuery, parameterSource, new QuestionBlockRowMapper()).stream().toList();
        for(var block: questionBlocks) {
            block.setQuestions(findQuestionBlockById(block.getId()).getQuestions());
        }
        return questionBlocks;
    }

    @Transactional
    public List<TaskBlock> findAllTaskBlocks() throws TaskBlockNotFoundException {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        String selectQuery = "select * from task_block as tb join test_to_block ttb on tb.id = ttb.task_block_id";
        List<TaskBlock> taskBlocks = namedParameterJdbcTemplate.query(selectQuery, parameterSource, new TaskBlockRowMapper()).stream().toList();
        for(var block: taskBlocks) {
            block.setTasks(findTaskBlockById(block.getId()).getTasks());
        }
        return taskBlocks;
    }

    @Transactional
    public Long createQuestionBlock(QuestionBlockCreateDto questionBlockDto) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        String createQuestionQuery = "insert into question_block(title) "+
                "values(:title)";
        mapSqlParameterSource.addValue("title", questionBlockDto.getTitle());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(createQuestionQuery, mapSqlParameterSource, keyHolder);
        Long id = (Long)keyHolder.getKeys().get("id");
        String addTaskToBlockQuery = "insert into question_block_to_question(question_block_id, question_id) " +
                "values(:task_block_id, :question_id)";
        mapSqlParameterSource.addValue("task_block_id", id);
        for(var question: questionBlockDto.getQuestionIds()) {
            mapSqlParameterSource.addValue("question_id", question);
            namedParameterJdbcTemplate.update(addTaskToBlockQuery, mapSqlParameterSource);
        }

        return id;
    }

    @Transactional
    public Long createTaskBlock(TaskBlockCreateDto taskBlock) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        String createQuestionQuery = "insert into task_block(title) "+
                "values(:title)";
        mapSqlParameterSource.addValue("title", taskBlock.getTitle());
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
}
