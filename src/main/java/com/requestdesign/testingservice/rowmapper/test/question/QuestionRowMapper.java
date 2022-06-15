package com.requestdesign.testingservice.rowmapper.test.question;

import com.requestdesign.testingservice.entity.test.Question;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class QuestionRowMapper implements RowMapper<Question> {
    @Override
    public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Question()
                .setId(rs.getLong("id"))
                .setText(rs.getString("text"))
                .setPicture(rs.getString("picture"));
    }
}
