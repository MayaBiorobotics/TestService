package com.requestdesign.testingservice.controller.result;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/result")
@Slf4j
public class ResultController implements ResultControllerInterface{
    private final ResultService resultService;

    @Autowired
    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }
    @Override
    public ResponseEntity getResultById(Integer resultId) {
        return null;
    }

    @Override
    public ResponseEntity getTaskFromResultById(Integer resultId, Integer taskId) {
        return null;
    }

    @Override
    public ResponseEntity getQuestionAnswerFromResultById(Integer resultId, Integer questionId) {
        return null;
    }
}
