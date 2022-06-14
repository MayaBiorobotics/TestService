package com.requestdesign.testingservice.repository.block;

import com.requestdesign.testingservice.dto.test.question.QuestionBlockCreateDto;
import com.requestdesign.testingservice.dto.test.task.TaskBlockCreateDto;
import com.requestdesign.testingservice.entity.test.QuestionBlock;
import com.requestdesign.testingservice.entity.test.Task;
import com.requestdesign.testingservice.entity.test.TaskBlock;
import com.requestdesign.testingservice.repository.task.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class BlockRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final TaskRepository taskRepository;

    @Autowired
    public BlockRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate, TaskRepository taskRepository) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.taskRepository = taskRepository;
    }

    public QuestionBlock findQuestionBlockById(Long blockId) {
    }

    public void editQuestionBlockById(Long blockId, QuestionBlockCreateDto questionBlockCreateDto) {
    }

    public TaskBlock findTaskBlockById() {
    }

    public void editTaskBlockById(Long blockId, TaskBlockCreateDto taskBlockCreateDto) {
    }

    public List<QuestionBlock> findAllQuestionBlocks() {
    }

    public List<TaskBlock> findAllTaskBlocks() {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        String selectAllQuery = "select * from task_block";

        List<Task> tasks = taskRepository.findAllTasks();
        for(var task: tasks) {

        }
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
}
