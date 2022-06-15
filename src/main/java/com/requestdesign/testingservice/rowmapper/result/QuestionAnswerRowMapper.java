package com.requestdesign.testingservice.rowmapper.result;

import com.requestdesign.testingservice.entity.result.QuestionAnswer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionAnswerRowMapper implements RowMapper<QuestionAnswer> {

    @Override
    public QuestionAnswer mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new QuestionAnswer()
                .setQuestionId(rs.getLong("question_id"))
                .setId(rs.getLong("question_result_id"))
                .setTestResultId(rs.getLong("result_id"))
                .setVariantId(rs.getLong("variant_id"));
    }
}
