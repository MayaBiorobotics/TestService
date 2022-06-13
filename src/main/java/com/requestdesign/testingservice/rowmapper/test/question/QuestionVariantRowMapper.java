package com.requestdesign.testingservice.rowmapper.test.question;

import com.requestdesign.testingservice.entity.test.QuestionVariant;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionVariantRowMapper implements RowMapper<QuestionVariant> {
    @Override
    public QuestionVariant mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new QuestionVariant()
                .setQuestionId(rs.getLong("question_id"))
                .setId(rs.getLong("id"))
                .setText(rs.getString("text"))
                .setPicture(rs.getString("picture"));
    }
}
