package com.requestdesign.testingservice.rowmapper.test.question;

import com.requestdesign.testingservice.entity.test.QuestionBlock;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionBlockRowMapper implements RowMapper<QuestionBlock> {
    @Override
    public QuestionBlock mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new QuestionBlock()
                .setId(rs.getLong("id"))
                .setTitle(rs.getString("title"));
    }
}
