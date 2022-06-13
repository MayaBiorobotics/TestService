package com.requestdesign.testingservice.controller.test;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface TestControllerInterface {
    @GetMapping("/{testId}")
    public ResponseEntity getTestById(@PathVariable Integer testId);
    
    @PutMapping("/{testId}")
    public ResponseEntity editTestById(@PathVariable Integer testId);

    @DeleteMapping("/{testId}")
    public ResponseEntity deleteTestById(@PathVariable Integer testId);

    @PutMapping("/{testId}/addtaskblock")
    public ResponseEntity addTaskToBlock(@PathVariable Integer testId, @RequestBody TaskBlockDto taskBlockDto);

    @GetMapping("/{testId}/phrase")
    public ResponseEntity getPhrasesFromTestById(@PathVariable Integer testId);

    @PostMapping("/{testId}/phrase")
    public ResponseEntity addPhraseToTest(@PathVariable Integer testId, @RequestBody PhraseDto phraseDto);

    @GetMapping("/task/{taskId}")
    public ResponseEntity getTaskById(@PathVariable Integer taskId);

    @PutMapping("/task/{taskId}")
    public ResponseEntity editTaskById(@PathVariable Integer taskId, @RequestBody TaskDto taskDto);

    @GetMapping("/block/question/{blockId}")
    public ResponseEntity getQuestinoBlockById(@PathVariable Integer blockId);

    @PutMapping("/block/question/{blockId}")
    public ResponseEntity editQuestionBlockById(@PathVariable BlockDto blockDto);

    @DeleteMapping("/block/question/{blockId}")
    public ResponseEntity deleteQuestionBlockById(@PathVariable Integer blockId);

    @GetMapping("/task")
    public ResponseEntity getAllTasks();

    @GetMapping("/block/task/{blockId}")
    public ResponseEntity getTaskBlockById(@PathVariable Integer blockId);

    @PutMapping("/block/task/{blockId}")
    public ResponseEntity editTaskBlockById(@PathVariable Integer blockId);

    @DeleteMapping("/block/task/{blockId}")
    public ResponseEntity deleteBlockTaskById(@PathVariable Integer blockId);

    @GetMapping("/block/question")
    public ResponseEntity getAllQuestionBlocks();

    @PostMapping("/block/question")
    public ResponseEntity createQuestionBlock(@RequestBody QuestionBlockDto questionBlockDto);

    @GetMapping("/block/task")
    public ResponseEntity getAllTaskBlocks();

    @PostMapping("/block/task")
    public ResponseEntity createTaskBlock(@RequestBody TaskBlockDto taskBlockDto);

    @GetMapping("/question")
    public ResponseEntity getAllQuestions();

    @PostMapping("/question")
    public ResponseEntity createQuestion(@RequestBody QuestionDto questionDto);

    @GetMapping("/question/{questionId}")
    public ResponseEntity getQuestionById(@PathVariable Integer questionId);

    @PostMapping("/question/{questionId}/addvariant")
    public ResponseEntity addVariantToQuestionById(@PathVariable Integer questionId);

    @PostMapping("/test")
    public ResponseEntity createTest(@RequestBody TestDto testDto);

    @PostMapping("/test")
    public ResponseEntity getAllTests();

    @GetMapping("/test/{testId}/phrase/{phraseId}")
    public ResponseEntity getPhraseFromTestById(@PathVariable Integer testId, @PathVariable Integer phraseId);

    @PutMapping("/test/{testId}/phrase/{phraseId}")
    public ResponseEntity editPhraseFromTestById(@PathVariable Integer testId, @PathVariable Integer phraseId);

    @PostMapping("/test/create/manual")
    public ResponseEntity createTestManually(@RequestBody TestManualDto testManualDto);
}
