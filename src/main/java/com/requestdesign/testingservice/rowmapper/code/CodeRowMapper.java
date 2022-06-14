package com.requestdesign.testingservice.rowmapper.code;

import com.requestdesign.testingservice.entity.phrase.Code;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CodeRowMapper implements RowMapper<Code> {
    @Override
    public Code mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Code()
                .setCode(rs.getString("code"))
                .setId(rs.getLong("id"))
                .setGiven(rs.getDate("given"))
                .setRespondentId(rs.getLong("respondent_id"))
                .setStatus(rs.getString("status"))
                .setResultId(rs.getLong("result_id"));
    }
}
