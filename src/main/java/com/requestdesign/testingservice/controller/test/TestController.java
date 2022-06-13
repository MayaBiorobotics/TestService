package com.requestdesign.testingservice.controller.test;

import com.requestdesign.testingservice.entity.test.Test;
import com.requestdesign.testingservice.service.test.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
    public ResponseEntity getTestById(Integer testId) {
        log.debug("get account with id = {}", testId);
        Test test = testService.findById(id);
        List<TestDTO> testList = new ArrayList<>();
        testList.add(new TestDTO(test));
        return new TestResponse()
                .setTestDTOList(testList);
    }

    @Override
    public ResponseEntity editTestById(Integer testId) {
        return null;
    }

    @Override
    public ResponseEntity deleteTestById(Integer testId) {
        return null;
    }

    @Override
    public ResponseEntity addTaskToBlock(Integer testId, TaskBlockDto taskBlockDto) {
        return null;
    }

    @Override
    public ResponseEntity getPhrasesFromTestById(Integer testId) {
        return null;
    }

    @Override
    public ResponseEntity addPhraseToTest(Integer testId, PhraseDto phraseDto) {
        return null;
    }

    @Override
    public ResponseEntity getTaskById(Integer taskId) {
        return null;
    }

    @Override
    public ResponseEntity editTaskById(Integer taskId, TaskDto taskDto) {
        return null;
    }

    @Override
    public ResponseEntity getQuestinoBlockById(Integer blockId) {
        return null;
    }

    @Override
    public ResponseEntity editQuestionBlockById(BlockDto blockDto) {
        return null;
    }

    @Override
    public ResponseEntity deleteQuestionBlockById(Integer blockId) {
        return null;
    }

    @Override
    public ResponseEntity getAllTasks() {
        return null;
    }

    @Override
    public ResponseEntity getTaskBlockById(Integer blockId) {
        return null;
    }

    @Override
    public ResponseEntity editTaskBlockById(Integer blockId) {
        return null;
    }

    @Override
    public ResponseEntity deleteBlockTaskById(Integer blockId) {
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
    public ResponseEntity getQuestionById(Integer questionId) {
        return null;
    }

    @Override
    public ResponseEntity addVariantToQuestionById(Integer questionId) {
        return null;
    }

    @Override
    public ResponseEntity createTest(TestDto testDto) {
        return null;
    }

    @Override
    public ResponseEntity getAllTests() {
        return null;
    }

    @Override
    public ResponseEntity getPhraseFromTestById(Integer testId, Integer phraseId) {
        return null;
    }

    @Override
    public ResponseEntity editPhraseFromTestById(Integer testId, Integer phraseId) {
        return null;
    }

    @Override
    public ResponseEntity createTestManually(TestManualDto testManualDto) {
        return null;
    }
}
