package com.requestdesign.testingservice.controller.statistics;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public interface StatisticsControllerInterface {
    @GetMapping("/")
    public ResponseEntity getAllStatistic();
}
