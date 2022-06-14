package com.requestdesign.testingservice.controller.test;

import com.requestdesign.testingservice.dto.test.TestCreateDto;
import com.requestdesign.testingservice.entity.test.Test;
import com.requestdesign.testingservice.exceptions.test.TestNotFoundException;
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
public class TestController implements TestControllerInterface{
    @Autowired
    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @Override
    public ResponseEntity getTestById(Long testId) {
        try {
            Test test = testService.findTestById(testId);
            return new ResponseEntity(test, HttpStatus.OK);
        } catch (TestNotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity editTestById(Long testId) {
        return null;
    }

    @Override
    public ResponseEntity deleteTestById(Long testId) {
        return null;
    }

    @Override
    public ResponseEntity addTaskToBlock(Long testId, TaskBlockDto taskBlockDto) {
        return null;
    }

    @Override
    public ResponseEntity getPhrasesFromTestById(Long testId) {
        return null;
    }

    @Override
    public ResponseEntity addPhraseToTest(Long testId, PhraseDto phraseDto) {
        return null;
    }

    @Override
    public ResponseEntity getTaskById(Long taskId) {
        return null;
    }

    @Override
    public ResponseEntity editTaskById(Long taskId, TaskDto taskDto) {
        return null;
    }

    @Override
    public ResponseEntity getQuestinoBlockById(Long blockId) {
        return null;
    }

    @Override
    public ResponseEntity editQuestionBlockById(BlockDto blockDto) {
        return null;
    }

    @Override
    public ResponseEntity deleteQuestionBlockById(Long blockId) {
        return null;
    }

    @Override
    public ResponseEntity getAllTasks() {
        return null;
    }

    @Override
    public ResponseEntity getTaskBlockById(Long blockId) {
        return null;
    }

    @Override
    public ResponseEntity editTaskBlockById(Long blockId) {
        return null;
    }

    @Override
    public ResponseEntity deleteBlockTaskById(Long blockId) {
        return null;
    }

    @Override
    public ResponseEntity getAllQuestionBlocks() {
        return null;
    }

    @Override
    public ResponseEntity createQuestionBlock(QuestionBlockDto questionBlockDto) {
        return null;
    }

    @Override
    public ResponseEntity getAllTaskBlocks() {
        return null;
    }

    @Override
    public ResponseEntity createTaskBlock(TaskBlockDto taskBlockDto) {
        return null;
    }

    @Override
    public ResponseEntity getAllQuestions() {
        return null;
    }

    @Override
    public ResponseEntity createQuestion(QuestionDto questionDto) {
        return null;
    }

    @Override
    public ResponseEntity getQuestionById(Long questionId) {
        return null;
    }

    @Override
    public ResponseEntity addVariantToQuestionById(Long questionId) {
        return null;
    }

    @Override
    public ResponseEntity createTest(TestDto testDto) {
        return null;
    }

    @Override
    public ResponseEntity getAllTests() {
        List<Test> tests = testService.findAllTests();
        return new ResponseEntity(tests, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getPhraseFromTestById(Long testId, Long phraseId) {
        return null;
    }

    @Override
    public ResponseEntity editPhraseFromTestById(Long testId, Long phraseId) {
        return null;
    }


    @Override
    public ResponseEntity createTestManually(TestManualDto testManualDto) {
        return null;
    }

    @PostMapping("/")
    public ResponseEntity createTest(@RequestBody TestCreateDto testCreateDto) {
        testService.saveTest(testCreateDto);
        return new ResponseEntity(HttpStatus.OK);
    }
}
