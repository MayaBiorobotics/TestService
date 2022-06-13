package com.requestdesign.testingservice.controller.test;

import com.requestdesign.testingservice.dto.test.TestCreateDto;
import com.requestdesign.testingservice.entity.test.Test;
import com.requestdesign.testingservice.service.test.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController{
    @Autowired
    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/")
    public List<Test> getAllTests() {
        List<Test> test = testService.findAllTests();
        return test;
    }

    @PostMapping("/")
    public ResponseEntity createTest(@RequestBody TestCreateDto testCreateDto) {
        testService.saveTest(testCreateDto);
        return new ResponseEntity(HttpStatus.OK);
    }
}
