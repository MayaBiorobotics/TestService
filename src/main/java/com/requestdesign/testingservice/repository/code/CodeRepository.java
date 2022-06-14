package com.requestdesign.testingservice.repository.code;

import com.requestdesign.testingservice.dto.code.CodeDto;
import com.requestdesign.testingservice.entity.phrase.Code;
import com.requestdesign.testingservice.repository.result.ResultRepository;
import com.requestdesign.testingservice.rowmapper.code.CodeRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class CodeRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final ResultRepository resultRepository;

    @Autowired
    public CodeRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate, ResultRepository resultRepository) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.resultRepository = resultRepository;
    }

    public List<Code> findAllCodes() {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        String selectAllQuery = "select * from codes";
        List<Code> codes = namedParameterJdbcTemplate.query(selectAllQuery, parameterSource, new CodeRowMapper()).stream().toList();
        return codes;
    }

    @Transactional
    public void generateCode(String code, CodeDto codeDto) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        String createCodeQuery = "insert into codes(code, respondent_id, status, given) values(:code, :respondent_id, :status, :given)";
        parameterSource.addValue("code", code);
        parameterSource.addValue("respondent_id", codeDto.getRespondentId());
        parameterSource.addValue("status", "not used");
        parameterSource.addValue("given", LocalDateTime.now());
        namedParameterJdbcTemplate.update(createCodeQuery, parameterSource);
        Long resultId = resultRepository.createDefaultResult(codeDto.getTestId(), codeDto.getRespondentId());
        String updateCodeQuery = "update codes set result_id = :result_id";
        parameterSource.addValue("result_id", resultId);
        namedParameterJdbcTemplate.update(updateCodeQuery, parameterSource);
    }
}
