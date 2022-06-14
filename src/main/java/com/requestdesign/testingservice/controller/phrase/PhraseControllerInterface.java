package com.requestdesign.testingservice.controller.phrase;

import com.requestdesign.testingservice.dto.phrase.PhraseDto;
import com.requestdesign.testingservice.dto.test.phrase.PhraseToTestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface PhraseControllerInterface {
    @GetMapping("/{phraseId}")
    public ResponseEntity getPhraseById(@PathVariable Long phraseId);

    @PutMapping("/{phraseId}")
    public ResponseEntity editPhraseById(@PathVariable Long phraseId, PhraseDto phraseDto);

    @GetMapping("/")
    public ResponseEntity getAllPhrases();

    @PostMapping("/")
    public ResponseEntity addPhraseToDictionary(@RequestBody PhraseDto phraseDto);
}
