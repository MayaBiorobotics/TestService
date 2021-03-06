package com.requestdesign.testingservice.rowmapper.test;

import com.requestdesign.testingservice.entity.test.Test;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class TestRowMapper implements RowMapper<Test> {

    @Override
    public Test mapRow(ResultSet rs, int rowNum) throws SQLException {
        Test test = new Test()
                .setTitle(rs.getString("title"))
                .setUrl(rs.getString("url"))
                .setRegion(rs.getString("region"))
                .setId(rs.getLong("id"))
                .setComment(rs.getString("comment"));
        return test;
    }
}
