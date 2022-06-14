package com.requestdesign.testingservice.controller.block;

import com.requestdesign.testingservice.dto.test.question.QuestionBlockCreateDto;
import com.requestdesign.testingservice.dto.test.task.TaskBlockCreateDto;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface BlockControllerInterface {
    @GetMapping("/question/{blockId}")
    public ResponseEntity getQuestionBlockById(@PathVariable Long blockId);

    @PutMapping("/question/{blockId}")
    public ResponseEntity editQuestionBlockById(@PathVariable Long blockId, @RequestBody QuestionBlockCreateDto questionBlockCreateDto);

    @GetMapping("/task/{blockId}")
    public ResponseEntity getTaskBlockById(@PathVariable Long blockId);

    @PutMapping("/task/{blockId}")
    public ResponseEntity editTaskBlockById(@PathVariable Long blockId, @RequestBody TaskBlockCreateDto taskBlockCreateDto);

    @PostMapping("/question")
    public ResponseEntity createQuestionBlock(@RequestBody QuestionBlockCreateDto questionBlockCreateDto);

    @GetMapping("/question")
    public ResponseEntity getAllQuestionBlock();

    @GetMapping("/task")
    public ResponseEntity getAllTaskBlocks();

    @PostMapping("/task")
    public ResponseEntity createTaskBlock(@RequestBody TaskBlockCreateDto taskBlockCreateDto);
}
