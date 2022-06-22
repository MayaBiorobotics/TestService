package com.requestdesign.testingservice;

import com.requestdesign.testingservice.controller.question.QuestionController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionControllerTests {
    @Autowired
    private QuestionController questionController;

    @Test
    public void createQuestion() {

    }

    @Test
    public void getQuestionById() {

    }

    @Test
    public void getIncorrectQuestionById() {

    }

    @Test
    public void getAllQuestions() {

    }
}
