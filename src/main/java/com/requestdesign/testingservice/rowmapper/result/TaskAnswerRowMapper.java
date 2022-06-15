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
                .setId(rs.getLong("task_result_id"))
                .setNumber(rs.getInt("number"))
                .setText(rs.getString("text"))
                .setTestResultId(rs.getLong("result_id"));
    }
}
