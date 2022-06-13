package com.requestdesign.testingservice.rowmapper.test.task;

import com.requestdesign.testingservice.entity.test.Task;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskRowMapper implements RowMapper<Task> {
    @Override
    public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Task()
                .setId(rs.getLong("id"))
                .setText(rs.getString("text"));
    }
}
