package com.requestdesign.testingservice.rowmapper.test;

import com.requestdesign.testingservice.entity.test.Test;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestRowMapper implements RowMapper<Test> {

    @Override
    public Test mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Test()
                .setId(rs.getLong("id"))
                .setTitle(rs.getString("title"))
                .setUrl(rs.getString("url"))
                .setComment(rs.getString("comment"))

    }
}
