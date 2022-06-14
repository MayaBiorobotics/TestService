package com.requestdesign.testingservice.rowmapper.test.task;

import com.requestdesign.testingservice.entity.test.TaskBlock;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskBlockRowMapper implements RowMapper<TaskBlock> {
    @Override
    public TaskBlock mapRow(ResultSet rs, int rowNum) throws SQLException {
        //TODO
        return null;
    }
}
