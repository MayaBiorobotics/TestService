package com.requestdesign.testingservice.controller.client;

import com.requestdesign.testingservice.dto.client.ClientTestResultDto;
import com.requestdesign.testingservice.entity.test.Test;
import com.requestdesign.testingservice.exceptions.block.TaskBlockNotFoundException;
import com.requestdesign.testingservice.exceptions.code.CodeNotFoundException;
import com.requestdesign.testingservice.exceptions.code.CodeUsedException;
import com.requestdesign.testingservice.exceptions.phrase.DictionaryPhraseNotFoundException;
import com.requestdesign.testingservice.exceptions.phrase.EmptyTestPhrasesException;
import com.requestdesign.testingservice.exceptions.test.TestNotFoundException;
import com.requestdesign.testingservice.service.client.ClientService;
import com.requestdesign.testingservice.service.test.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.ReactivePageableHandlerMethodArgumentResolver;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientController implements ClientControllerInterface{
    @Autowired
    private TestService testService;

    @Autowired
    private ClientService clientService;

    @Override
    public ResponseEntity getTest(Long testId) {
        try {
            Test test = testService.findTestById(testId);
            test.setPhrases(clientService.configurePhrasesForTest(testId));
            System.out.println(test.getPhrases());
            return new ResponseEntity(test, HttpStatus.OK);
        } catch (TestNotFoundException | TaskBlockNotFoundException | DictionaryPhraseNotFoundException |
                 EmptyTestPhrasesException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity sendResult(@RequestBody ClientTestResultDto clientTestResult) {
        try {
            System.out.println(clientTestResult);
            clientService.saveResult(clientTestResult);
            return new ResponseEntity(HttpStatus.OK);
        } catch (CodeNotFoundException e) {
            return new ResponseEntity("Неверный код", HttpStatus.BAD_REQUEST);
        } catch (CodeUsedException e) {
            return new ResponseEntity("Код уже был использован", HttpStatus.BAD_REQUEST);
        }
    }
}
