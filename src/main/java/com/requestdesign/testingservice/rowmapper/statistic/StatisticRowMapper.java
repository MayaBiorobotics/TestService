package com.requestdesign.testingservice.rowmapper.statistic;

import com.requestdesign.testingservice.entity.statistics.Statistic;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StatisticRowMapper implements RowMapper<Statistic> {
    @Override
    public Statistic mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Statistic()
                .setId(rs.getLong("id"))
                .setDate(rs.getString("date"))
                .setClickAmount(rs.getInt(""))
    }
}
