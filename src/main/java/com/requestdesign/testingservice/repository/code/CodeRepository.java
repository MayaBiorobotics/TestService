package com.requestdesign.testingservice.repository.code;

import com.requestdesign.testingservice.dto.code.CodeDto;
import com.requestdesign.testingservice.entity.phrase.Code;
import com.requestdesign.testingservice.exceptions.code.CodeNotFoundException;
import com.requestdesign.testingservice.repository.result.ResultRepository;
import com.requestdesign.testingservice.rowmapper.code.CodeRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    @Transactional
    public Code findCode(String codeString) throws CodeNotFoundException {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        String selectQuery = "select * from codes where code = :code";
        parameterSource.addValue("code", codeString);
        Optional<Code> code = namedParameterJdbcTemplate.query(selectQuery, parameterSource, new CodeRowMapper()).stream().findFirst();
        if(code.isEmpty()) {
            throw new CodeNotFoundException("Такого кода нет");
        }
        return code.get();
    }

    public void setAsUsed(String code) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        String updateQuery = "update codes set status = 'used' where code = :code";
        parameterSource.addValue("code", code);
        namedParameterJdbcTemplate.update(updateQuery, parameterSource);
    }
}
