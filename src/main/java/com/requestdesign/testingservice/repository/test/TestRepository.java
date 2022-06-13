package com.requestdesign.testingservice.repository.test;

import com.requestdesign.testingservice.entity.test.Test;
import com.requestdesign.testingservice.rowmapper.test.TestRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TestRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public TestRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Test findById(Long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        String selectByIdQuery = "select " +
                "test.title as title, " +
                "test.region as region, " +
                "test.url as url, " +
                "qb.id as question_block_id, " +
                "qb.title as question_block_title, " +
                "ttb.number as task_block_number, " +
                "q.id as question_id, " +
                "q.text as question_text, " +
                "q.picture as question_picture, " +
                "task.id as task_id, " +
                "task.text as task_text " +
                "from test as test " +
                "join test_to_question_block as ttqb " +
                "on ttqb.test_id = test.id " +
                "join test_to_block as ttb " +
                "on ttb.test_id = test.id " +
                "join question_block as qb " +
                "on qb.id = ttqb.question_block_id " +
                "join task_block as tb " +
                "on tb.id = ttb.task_block_id " +
                "join question_block_to_question as qbtq " +
                "on qbtq.question_block_id = qb.id " +
                "join question as q " +
                "on q.id = qbtq.question_id " +
                "join task_block_to_task as tbtt " +
                "on tb.id = tbtt.task_block_id " +
                "join task as task " +
                "on task.id = tbtt.task_id ";
        parameterSource.addValue("id", id);
        return namedParameterJdbcTemplate.query(selectByIdQuery, parameterSource, new TestRowMapper()).stream().findFirst().orElse(null);
    }
}
