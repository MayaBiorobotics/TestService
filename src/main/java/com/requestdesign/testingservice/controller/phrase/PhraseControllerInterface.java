package com.requestdesign.testingservice.controller.phrase;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface PhraseControllerInterface {
    @GetMapping("/phrase/{phraseId}")
    public ResponseEntity getPhraseById(@PathVariable Integer phraseId);

    @PutMapping("/phrase/{phraseId}")
    public ResponseEntity editPhraseById(@PathVariable Integer phraseId);

    @GetMapping("/phrase")
    public ResponseEntity getAllPhrases();
}
