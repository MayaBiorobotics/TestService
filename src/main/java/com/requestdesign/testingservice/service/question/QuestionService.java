package com.requestdesign.testingservice.service.question;

import com.requestdesign.testingservice.dto.test.question.QuestionCreateDto;
import com.requestdesign.testingservice.dto.test.question.QuestionVariantAddDto;
import com.requestdesign.testingservice.entity.test.Question;
import com.requestdesign.testingservice.exceptions.test.QuestionNotFoundException;
import com.requestdesign.testingservice.repository.question.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> getAllQuestions() {
        List<Question> questions = questionRepository.findAllQuestions();
        return questions;
    }

    public void createQuestion(QuestionCreateDto questionCreateDto) {
        Long id = questionRepository.createQuestion(questionCreateDto);
    }

    public Question getQuestionById(Long id) throws QuestionNotFoundException {
        Question question = questionRepository.findQuestionById(id);
        return question;
    }

    public void addVariantToQuestion(Long questionId, QuestionVariantAddDto questionVariantAddDto) {
        questionRepository.addVariantToQuestion(questionVariantAddDto);
    }
}
