package com.requestdesign.testingservice.rowmapper.test.question;

import com.requestdesign.testingservice.entity.test.Question;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class QuestionRowMapper implements RowMapper<Question> {
    @Override
    public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
        Question question = new Question()
                .setId(rs.getLong("question_id"))
                .setText(rs.getString("question_text"))
                .setPicture(rs.getString("question_picture"));
        return question;
    }
}
