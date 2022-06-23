package com.requestdesign.testingservice.rowmapper.result;

import com.requestdesign.testingservice.entity.result.TaskAnswer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskAnswerRowMapper implements RowMapper<TaskAnswer> {
    @Override
    public TaskAnswer mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new TaskAnswer()
                .setTaskId(rs.getLong("task_id"))
                .setId(rs.getLong("id"))
                .setText(rs.getString("text"))
                .setTestResultId(rs.getLong("test_result_id"));
    }
}
