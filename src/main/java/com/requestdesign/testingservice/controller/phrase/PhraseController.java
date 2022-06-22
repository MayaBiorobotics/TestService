package com.requestdesign.testingservice.controller.phrase;

import com.requestdesign.testingservice.dto.phrase.PhraseDto;
import com.requestdesign.testingservice.dto.test.phrase.PhraseGroupDto;
import com.requestdesign.testingservice.dto.test.phrase.PhraseToTestDto;
import com.requestdesign.testingservice.entity.phrase.DictionaryPhrase;
import com.requestdesign.testingservice.entity.phrase.TestPhrase;
import com.requestdesign.testingservice.exceptions.phrase.DictionaryPhraseNotFoundException;
import com.requestdesign.testingservice.service.phrase.PhraseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/phrase")
@Slf4j
public class PhraseController implements PhraseControllerInterface{
    private final PhraseService phraseService;

    @Autowired
    public PhraseController(PhraseService phraseService) {
        this.phraseService = phraseService;
    }

    @Override
    public ResponseEntity getPhraseById(Long phraseId) {
        DictionaryPhrase phrase = null;
        try {
            phrase = phraseService.findPhraseById(phraseId);
        } catch (DictionaryPhraseNotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(phrase, HttpStatus.OK);
    }

    @Override
    public ResponseEntity editPhraseById(Long phraseId, PhraseDto phraseDto){
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity getAllPhrases() {
        List<DictionaryPhrase> phrases = phraseService.findAllPhrases();
        return new ResponseEntity(phrases, HttpStatus.OK);
    }

    @Override
    public ResponseEntity addPhraseToDictionary(PhraseDto phraseDto) {
        phraseService.addPhraseToDictionary(phraseDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity addPhraseGroup(PhraseGroupDto phraseGroupDto) {
        phraseService.addPhraseGroup(phraseGroupDto);
        return new ResponseEntity(HttpStatus.OK);
    }
}
