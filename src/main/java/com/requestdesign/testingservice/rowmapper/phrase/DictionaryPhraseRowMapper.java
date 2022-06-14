package com.requestdesign.testingservice.rowmapper.phrase;

import com.requestdesign.testingservice.entity.phrase.DictionaryPhrase;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DictionaryPhraseRowMapper implements RowMapper<DictionaryPhrase> {
    @Override
    public DictionaryPhrase mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new DictionaryPhrase()
                .setId(rs.getLong("id"))
                .setPhrase(rs.getString("phrase"))
                .setDirectionId(rs.getLong("group_id"));
    }
}
