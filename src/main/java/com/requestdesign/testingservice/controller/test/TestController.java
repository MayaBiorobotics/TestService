package com.requestdesign.testingservice.controller.test;

import com.requestdesign.testingservice.dto.test.TaskBlockNumberDto;
import com.requestdesign.testingservice.dto.test.TestCreationDto;
import com.requestdesign.testingservice.dto.test.TestManuallyCreateDto;
import com.requestdesign.testingservice.dto.test.phrase.PhraseToTestDto;
import com.requestdesign.testingservice.entity.phrase.TestPhrase;
import com.requestdesign.testingservice.entity.test.Test;
import com.requestdesign.testingservice.exceptions.block.TaskBlockNotFoundException;
import com.requestdesign.testingservice.exceptions.phrase.PhraseNotFoundException;
import com.requestdesign.testingservice.exceptions.test.TestNotFoundException;
import com.requestdesign.testingservice.service.test.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/test")
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
        } catch (TaskBlockNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity editTestById(Long testId) {
        //TODO
        return null;
    }

    @Override
    public ResponseEntity addTaskBlockToTest(Long testId, TaskBlockNumberDto taskBlockNumber) {
        testService.addTaskBlockToTest(testId, taskBlockNumber);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity getAllPhrasesOfTest(Long testId) {
        List<TestPhrase> testPhrases = testService.findAllPhrasesFromTest(testId);
        return new ResponseEntity(testPhrases, HttpStatus.OK);
    }

    @Override
    public ResponseEntity addPhraseToTest(Long testId, PhraseToTestDto phraseAddToTestDto) {
        testService.addPhraseToTest(testId, phraseAddToTestDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity getAllTests() {
        List<Test> tests = testService.findAllTests();
        return new ResponseEntity(tests, HttpStatus.OK);
    }

    @Override
    public ResponseEntity createTest(TestCreationDto testCreateDto) {
        System.out.println(testCreateDto);
        Long id = testService.saveTest(testCreateDto);
        return new ResponseEntity(id, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getPhraseFromTest(Long testId, Long phraseId) {
        try {
            TestPhrase testPhrase = testService.findPhraseFromTestById(testId, phraseId);
            return new ResponseEntity(testPhrase, HttpStatus.OK);
        } catch (PhraseNotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity editPhraseInTest(Long testId, PhraseToTestDto phraseToTest) {
        testService.editPhraseInTest(testId, phraseToTest);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity createTestManually(TestManuallyCreateDto testManuallyCreateDto) {
        Long id = testService.saveTestManually(testManuallyCreateDto);
        return new ResponseEntity(id, HttpStatus.OK);
    }
}
