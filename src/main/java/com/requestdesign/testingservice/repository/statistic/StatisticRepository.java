package com.requestdesign.testingservice.repository.statistic;

import com.requestdesign.testingservice.entity.statistics.Statistic;
import com.requestdesign.testingservice.exceptions.statistic.StatisticNotFound;
import com.requestdesign.testingservice.rowmapper.statistic.StatisticRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StatisticRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public StatisticRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Statistic findStatisticById(Long id) throws StatisticNotFound {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        String selectByIdQuery = "select * from test_statistics where id = :id";
        parameterSource.addValue("id", id);
        Optional<Statistic> statistic = namedParameterJdbcTemplate.query(selectByIdQuery, parameterSource, new StatisticRowMapper()).stream().findFirst();
        if(statistic.isEmpty()) {
            throw new StatisticNotFound("Статистики с таким id нет");
        } else {
            return statistic.get();
        }
    }

    public List<Statistic> findStatisticsByTestId(Long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        String selectByIdQuery = "select * from test_statistics where id = :id";
        parameterSource.addValue("id", id);
        List<Statistic> statistics = namedParameterJdbcTemplate.query(selectByIdQuery, parameterSource, new StatisticRowMapper()).stream().toList();
        return statistics;
    }

    public List<Statistic> findAll() {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        String selectQuery = "select * from test_statistics";
        List<Statistic> statistics = namedParameterJdbcTemplate.query(selectQuery, parameterSource, new StatisticRowMapper()).stream().toList();
        return statistics;
    }
}
