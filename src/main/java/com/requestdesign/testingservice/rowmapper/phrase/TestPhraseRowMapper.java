package com.requestdesign.testingservice.rowmapper.phrase;

import com.requestdesign.testingservice.entity.phrase.TestPhrase;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestPhraseRowMapper implements RowMapper<TestPhrase> {
    @Override
    public TestPhrase mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new TestPhrase()
                .setPhraseId(rs.getLong("phrase_id"))
                .setTestId(rs.getLong("test_id"))
                .setIntensivity(rs.getInt("intensivity"))
                .setId(rs.getLong("id"));
    }
}
