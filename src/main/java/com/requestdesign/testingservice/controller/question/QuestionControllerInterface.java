package com.requestdesign.testingservice.controller.question;


import com.requestdesign.testingservice.dto.test.question.QuestionCreateDto;
import com.requestdesign.testingservice.dto.test.question.QuestionVariantAddDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface QuestionControllerInterface {
    @GetMapping("/")
    public ResponseEntity getAllQuestions();

    @PostMapping("/")
    public ResponseEntity createQuestion(@RequestBody QuestionCreateDto questionCreateDto);

    @GetMapping("/{questionId}")
    public ResponseEntity getQuestionById(@PathVariable Long questionId);

    @PostMapping("/{questionId}/addvariant")
    public ResponseEntity addVariantToQuestion(@PathVariable Long questionId, @RequestBody QuestionVariantAddDto questionVariantAddDto);
}
