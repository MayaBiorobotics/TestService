package com.requestdesign.testingservice.rowmapper.test;

import com.requestdesign.testingservice.entity.test.Test;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SimpleTestRowMapper implements RowMapper<Test> {
    @Override
    public Test mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Test()
                .setId(rs.getLong("id"))
                .setUrl(rs.getString("url"))
                .setTitle(rs.getString("title"))
                .setComment(rs.getString("comment"))
                .setRegion(rs.getString("region"));
    }
}
