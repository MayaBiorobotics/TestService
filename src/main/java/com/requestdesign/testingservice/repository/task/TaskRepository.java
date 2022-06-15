package com.requestdesign.testingservice.repository.task;

import com.requestdesign.testingservice.dto.test.task.TaskDto;
import com.requestdesign.testingservice.entity.test.Task;
import com.requestdesign.testingservice.exceptions.test.TaskNotFoundException;
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
public class TaskRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public TaskRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Set<Task> findTasksByTestId(Long id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        String selectByIdQuery = "";
        mapSqlParameterSource.addValue("id", id);
        return new HashSet<>(namedParameterJdbcTemplate.query(selectByIdQuery, mapSqlParameterSource, new TaskRowMapper()));
    }

    public Task findTaskById(Long id) throws TaskNotFoundException {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        String selectByIdQuery = "select * from task where task.id = :id";
        mapSqlParameterSource.addValue("id", id);
        Optional<Task> task = namedParameterJdbcTemplate.query(selectByIdQuery, mapSqlParameterSource, new TaskRowMapper()).stream().findFirst();
        if(task.isPresent()) {
            return task.get();
        } else {
            throw new TaskNotFoundException("Задания с таким id нет");
        }
    }

    public List<Task> findAllTasks() {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        String selectAllQuery = "select * from task";
        return new ArrayList<>(namedParameterJdbcTemplate.query(selectAllQuery, mapSqlParameterSource, new TaskRowMapper()).stream().toList());
    }

    @Transactional
    public Long createTask(TaskDto task) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        String createTaskQuery = "insert into task(text) " +
                "values(:text)";
        mapSqlParameterSource.addValue("text", task.getText());
        KeyHolder holder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(createTaskQuery, mapSqlParameterSource, holder);
        Long id = (Long)holder.getKeys().get("id");
        return id;
    }

    public void editTaskById(Long taskId, TaskDto taskDto) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        String editTaskQuery = "update task set text = :task_text where id = :task_id";
        parameterSource.addValue("task_id", taskId);
        parameterSource.addValue("task_text", taskDto.getText());
        namedParameterJdbcTemplate.update(editTaskQuery, parameterSource);
    }
}
