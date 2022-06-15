package com.requestdesign.testingservice.service.statistic;

import com.requestdesign.testingservice.entity.statistics.Statistic;
import com.requestdesign.testingservice.repository.statistic.StatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticService {
    private final StatisticRepository statisticRepository;

    @Autowired
    public StatisticService(StatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }

    public List<Statistic> findAll() {
        List<Statistic> statistics = statisticRepository.findAll();
        return statistics;
    }
}
