package com.requestdesign.testingservice.repository.code;

import com.requestdesign.testingservice.entity.phrase.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CodeRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public CodeRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Code> findAllCodes() {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        String selectAllQuery = "select * from codes";
        //TODO
        return null;
    }

    public void addCode(String code, CodeDto codeDto) {

    }
}
