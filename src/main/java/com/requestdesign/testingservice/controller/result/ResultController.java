package com.requestdesign.testingservice.controller.result;

import com.requestdesign.testingservice.entity.result.QuestionAnswer;
import com.requestdesign.testingservice.entity.result.TaskAnswer;
import com.requestdesign.testingservice.entity.result.TestResult;
import com.requestdesign.testingservice.exceptions.result.QuestionAnswerNotFoundException;
import com.requestdesign.testingservice.exceptions.result.ResultNotFoundException;
import com.requestdesign.testingservice.exceptions.result.TaskAnswerNotFoundException;
import com.requestdesign.testingservice.service.result.ResultService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/result")
@Slf4j
public class ResultController implements ResultControllerInterface{
    private final ResultService resultService;

    @Autowired
    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }
    @Override
    public ResponseEntity getResultById(Long resultId) {
        TestResult testResult = null;
        try {
            testResult = resultService.findById(resultId);
        } catch (ResultNotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(testResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getTaskFromResultById(Long resultId, Long taskId) {
        TaskAnswer taskAnswer = null;
        try {
            taskAnswer = resultService.findTaskFromResultById(resultId, taskId);
        } catch (TaskAnswerNotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(taskAnswer, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getQuestionAnswerFromResultById(Long resultId, Long questionId) {
        QuestionAnswer questionAnswer = null;
        try {
            questionAnswer = resultService.findQuestionAnswerById(resultId, questionId);
        } catch (QuestionAnswerNotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(questionAnswer, HttpStatus.OK);
    }
}
