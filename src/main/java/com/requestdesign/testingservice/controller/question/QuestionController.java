package com.requestdesign.testingservice.controller.question;

import com.requestdesign.testingservice.dto.test.question.QuestionCreateDto;
import com.requestdesign.testingservice.dto.test.question.QuestionVariantAddDto;
import com.requestdesign.testingservice.entity.test.Question;
import com.requestdesign.testingservice.exceptions.test.QuestionNotFoundException;
import com.requestdesign.testingservice.service.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/question")
public class QuestionController implements QuestionControllerInterface{
    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public ResponseEntity getAllQuestions() {
        List<Question> questions = questionService.getAllQuestions();
        return new ResponseEntity(questions, HttpStatus.OK);
    }

    @Override
    public ResponseEntity createQuestion(QuestionCreateDto questionCreateDto) {
        questionService.createQuestion(questionCreateDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity getQuestionById(Long questionId) {
        Question question = null;
        try {
            question = questionService.getQuestionById(questionId);
            return new ResponseEntity(question, HttpStatus.OK);
        } catch (QuestionNotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity addVariantToQuestion(Long questionId, QuestionVariantAddDto questionVariantAddDto) {
        questionService.addVariantToQuestion(questionId, questionVariantAddDto);
        return new ResponseEntity(HttpStatus.OK);
    }
}
