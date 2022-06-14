package com.requestdesign.testingservice.repository.result;

import com.requestdesign.testingservice.dto.result.ResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ResultRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public ResultRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void findResultById(Long id) {

    }

    @Transactional
    public Long createDefaultResult(Long testId, Long respondentId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        String createResultQuery = "insert into test_result(test_id, respondent_id) values(:test_id, :respondent_id)";
        parameterSource.addValue("test_id", testId);
        parameterSource.addValue("respondent_id", respondentId);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(createResultQuery, parameterSource, keyHolder);
        return (Long)keyHolder.getKeys().get("id");
    }
}
