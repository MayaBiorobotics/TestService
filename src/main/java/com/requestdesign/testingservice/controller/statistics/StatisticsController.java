package com.requestdesign.testingservice.controller.statistics;

import com.requestdesign.testingservice.entity.statistics.Statistic;
import com.requestdesign.testingservice.service.statistic.StatisticService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistics")
@Slf4j
public class StatisticsController implements StatisticsControllerInterface {
    private final StatisticService statisticService;

    @Autowired
    public StatisticsController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }


    @Override
    public ResponseEntity getAllStatistic() {
        List<Statistic> statistics = statisticService.findAll();
        return new ResponseEntity(statistics, HttpStatus.OK);
    }
}
