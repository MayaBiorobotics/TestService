package com.requestdesign.testingservice.rowmapper.test.task;

import com.requestdesign.testingservice.entity.test.TaskBlock;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskBlockInTestRowMapper implements RowMapper<TaskBlock> {
    @Override
    public TaskBlock mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new TaskBlock()
                .setId(rs.getLong("task_block_id"))
                .setTitle(rs.getString("title"))
                .setNumber(rs.getInt("number"));
    }
}
