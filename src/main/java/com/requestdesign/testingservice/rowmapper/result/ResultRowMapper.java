package com.requestdesign.testingservice.rowmapper.result;

import com.requestdesign.testingservice.entity.result.TestResult;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultRowMapper implements RowMapper<TestResult> {
    @Override
    public TestResult mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new TestResult()
                .setId(rs.getLong("id"))
                .setDatetime(rs.getDate("creation_time"))
                .setRespondentId(rs.getLong("respondent_id"))
                .setRespondentType(rs.getInt("respondent_type"))
                .setTestId(rs.getLong("test_id"));
    }
}
