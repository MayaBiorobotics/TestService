package com.requestdesign.testingservice.controller.test;

import com.requestdesign.testingservice.dto.test.TaskBlockNumberDto;
import com.requestdesign.testingservice.dto.test.TestCreationDto;
import com.requestdesign.testingservice.dto.test.TestManuallyCreateDto;
import com.requestdesign.testingservice.dto.test.phrase.PhraseToTestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface TestControllerInterface {
    @GetMapping("/{testId}")
    public ResponseEntity getTestById(@PathVariable Long testId);

    @PutMapping("/{testId}")
    public ResponseEntity editTestById(@PathVariable Long testId);

    @PutMapping("/{testId}/addtaskblock")
    public ResponseEntity addTaskBlockToTest(@PathVariable Long testId, @RequestBody TaskBlockNumberDto taskBlockNumber);

    @GetMapping("/{testId}/phrase")
    public ResponseEntity getAllPhrasesOfTest(@PathVariable Long testId);

    @PostMapping("/{testId}/phrase")
    public ResponseEntity addPhraseToTest(@PathVariable Long testId, @RequestBody PhraseToTestDto phraseAddToTestDto);

    @GetMapping("/")
    public ResponseEntity getAllTests();

    @PostMapping("/")
    public ResponseEntity createTest(@RequestBody TestCreationDto testCreateDto);

    @GetMapping("/{testId}/phrase/{phraseId}")
    public ResponseEntity getPhraseFromTest(@PathVariable Long testId, @PathVariable Long phraseId);

    @PutMapping("/{testId}/phrase")
    public ResponseEntity editPhraseInTest(@PathVariable Long testId, @RequestBody PhraseToTestDto phraseToTest);

    @PostMapping("/create/manual")
    public ResponseEntity createTestManually(@RequestBody TestManuallyCreateDto testManuallyCreateDto);
}
