package com.requestdesign.testingservice.controller.result;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface ResultControllerInterface {
    @GetMapping("/result/{resultId}")
    public ResponseEntity getResultById(@PathVariable Long resultId);

    @GetMapping("/result/{resultId}/task/{taskId}")
    public ResponseEntity getTaskFromResultById(@PathVariable Long resultId, @PathVariable Long taskId);

    @GetMapping("/result/{resultId}/question/{questionId}")
    public ResponseEntity getQuestionAnswerFromResultById(@PathVariable Long resultId, @PathVariable Long questionId);
}
