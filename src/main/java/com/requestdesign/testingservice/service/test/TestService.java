package com.requestdesign.testingservice.service.test;

import com.requestdesign.testingservice.dto.test.TaskBlockNumberDto;
import com.requestdesign.testingservice.dto.test.TestCreationDto;
import com.requestdesign.testingservice.dto.test.TestManuallyCreateDto;
import com.requestdesign.testingservice.dto.test.phrase.PhraseToTestDto;
import com.requestdesign.testingservice.entity.phrase.TestPhrase;
import com.requestdesign.testingservice.entity.test.Test;
import com.requestdesign.testingservice.exceptions.block.TaskBlockNotFoundException;
import com.requestdesign.testingservice.exceptions.phrase.PhraseNotFoundException;
import com.requestdesign.testingservice.exceptions.test.TestNotFoundException;
import com.requestdesign.testingservice.repository.test.TestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TestService {
    @Autowired
    private final TestRepository testRepository;

    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public Test findTestById(Long id) throws TestNotFoundException, TaskBlockNotFoundException {
        Test test = testRepository.findById(id);
        return test;
    }

    public List<Test> findAllTests() {
        List<Test> tests = testRepository.findAllTests();
        return tests;
    }

    public Long saveTest(TestCreationDto test) {
        Long id = testRepository.saveTest(test);
        return id;
    }

    public Long saveTestManually(TestManuallyCreateDto testManuallyCreateDto) {
        Long id = testRepository.createTestManually(testManuallyCreateDto);
        return id;
    }

    public TestPhrase findPhraseFromTestById(Long testId, Long phraseId) throws PhraseNotFoundException {
        TestPhrase testPhrase = testRepository.findPhraseFromTestById(testId, phraseId);
        return testPhrase;
    }

    public void addPhraseToTest(Long testId, PhraseToTestDto phraseAddToTestDto) {
        testRepository.addPhraseToTest(testId, phraseAddToTestDto);
    }

    public List<TestPhrase> findAllPhrasesFromTest(Long testId) {
        List<TestPhrase> testPhrases = testRepository.findAllTestPhrasesFromTest(testId);
        return testPhrases;
    }

    public void addTaskBlockToTest(Long testId, TaskBlockNumberDto taskBlockNumber) {
        testRepository.addTaskBlockToTestById(testId, taskBlockNumber);
    }

    public void editPhraseInTest(Long testId, PhraseToTestDto phraseToTest) {
        testRepository.editPhraseInTest(testId, phraseToTest);
    }

    private void putPhraseIntoTest(Long testId) {
        List<TestPhrase> testPhrases = testRepository.findAllTestPhrasesFromTest(testId);
        
    }
}
