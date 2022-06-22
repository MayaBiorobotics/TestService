package com.requestdesign.testingservice;

import com.requestdesign.testingservice.controller.result.ResultController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ResultControllerTests {
    @Autowired
    private ResultController resultController;

    @Test
    public void getFullResult() {

    }

    @Test
    public void getIncorrectFullResult() {

    }

    @Test
    public void getQuestionBlockAnswers() {

    }

    @Test
    public void getTaskBlockAnswers() {

    }
}
