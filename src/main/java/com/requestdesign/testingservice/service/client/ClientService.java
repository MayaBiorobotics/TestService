package com.requestdesign.testingservice.service.client;

import com.requestdesign.testingservice.dto.client.ClientTestResultDto;
import com.requestdesign.testingservice.dto.test.phrase.PhraseToTestDto;
import com.requestdesign.testingservice.entity.phrase.Code;
import com.requestdesign.testingservice.entity.phrase.DictionaryPhrase;
import com.requestdesign.testingservice.entity.phrase.TestPhrase;
import com.requestdesign.testingservice.entity.test.Test;
import com.requestdesign.testingservice.exceptions.block.TaskBlockNotFoundException;
import com.requestdesign.testingservice.exceptions.code.CodeNotFoundException;
import com.requestdesign.testingservice.exceptions.code.CodeUsedException;
import com.requestdesign.testingservice.exceptions.phrase.DictionaryPhraseNotFoundException;
import com.requestdesign.testingservice.exceptions.phrase.EmptyTestPhrasesException;
import com.requestdesign.testingservice.exceptions.test.TestNotFoundException;
import com.requestdesign.testingservice.repository.result.ResultRepository;
import com.requestdesign.testingservice.repository.test.TestRepository;
import com.requestdesign.testingservice.service.code.CodeService;
import com.requestdesign.testingservice.service.phrase.PhraseService;
import com.requestdesign.testingservice.service.test.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClientService {
    private static int STANDART_PHRASE_COUNT = 2;
    @Autowired
    private TestRepository testRepository;

    @Autowired
    private PhraseService phraseService;
    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private TestService testService;

    @Autowired
    private CodeService codeService;

    public void saveResult(ClientTestResultDto result) throws CodeNotFoundException, CodeUsedException {
        Code code = codeService.getCode(result.getCode());
        if (Objects.equals(code.getStatus(), "used")) {
            throw new CodeUsedException("Код использован");
        }
        resultRepository.saveResult(code.getResultId(), result);
        codeService.setAsUsed(result.getCode());
    }

    public Test getTestById(Long testId) throws TaskBlockNotFoundException, TestNotFoundException, DictionaryPhraseNotFoundException, EmptyTestPhrasesException {
        Test test = testService.findTestById(testId);
        test.setPhrases(configurePhrasesForTest(testId));
        return test;
    }

    public Set<String> configurePhrasesForTest(Long testId) throws DictionaryPhraseNotFoundException, EmptyTestPhrasesException {
        List<TestPhrase> testPhrases = testService.findAllPhrasesFromTest(testId);
        System.out.println(testPhrases.size());
        Set<String> resultPhrases = new HashSet<>();
        if (testPhrases.size() == 0) {
            throw new EmptyTestPhrasesException("Для теста нет фраз");
        }
        if (testPhrases.size() <= STANDART_PHRASE_COUNT) {
            for (var testPhrase : testPhrases) {
                if (testPhrase.getIntensivity() != 0) {
                    DictionaryPhrase phrase = phraseService.findPhraseById(testPhrase.getPhraseId());
                    testPhrase.setIntensivity(testPhrase.getIntensivity() - 1);
                    testRepository.editPhraseInTest(testId, new PhraseToTestDto(testPhrase.getPhraseId(), testPhrase.getIntensivity()));
                    resultPhrases.add(phrase.getPhrase());
                }
            }
            return resultPhrases;
        } else {
            testPhrases.sort(Comparator.comparing(TestPhrase::getIntensivity));
            for (int i = 0; i < STANDART_PHRASE_COUNT; i++) {
                TestPhrase testPhrase = testPhrases.get(i);
                if (testPhrases.get(i).getIntensivity() != 0) {
                    DictionaryPhrase phrase = phraseService.findPhraseById(testPhrase.getPhraseId());
                    testPhrase.setIntensivity(testPhrase.getIntensivity() - 1);
                    testRepository.editPhraseInTest(testId, new PhraseToTestDto(testPhrase.getPhraseId(), testPhrase.getIntensivity()));
                    resultPhrases.add(phrase.getPhrase());
                }
            }
            return resultPhrases;
        }
    }
}
