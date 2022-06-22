package com.requestdesign.testingservice.service.phrase;

import com.requestdesign.testingservice.controller.phrase.PhraseControllerInterface;
import com.requestdesign.testingservice.dto.phrase.PhraseDto;
import com.requestdesign.testingservice.dto.test.phrase.PhraseGroupDto;
import com.requestdesign.testingservice.dto.test.phrase.PhraseToTestDto;
import com.requestdesign.testingservice.entity.phrase.DictionaryPhrase;
import com.requestdesign.testingservice.entity.phrase.TestPhrase;
import com.requestdesign.testingservice.exceptions.phrase.DictionaryPhraseNotFoundException;
import com.requestdesign.testingservice.repository.phrase.PhraseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhraseService {
    private final PhraseRepository phraseRepository;
    
    @Autowired 
    public PhraseService(PhraseRepository phraseRepository) {
        this.phraseRepository = phraseRepository;
    }

    public void addPhraseToDictionary(PhraseDto phraseDto) {
        phraseRepository.addPhraseToDictionary(phraseDto);
    }

    public List<DictionaryPhrase> findAllPhrases() {
        List<DictionaryPhrase> testPhrases = phraseRepository.findAllPhrases();
        return testPhrases;
    }

    public void editPhraseById(Long phraseId, PhraseDto phraseDto) {
        phraseRepository.editPhraseInDictionaryById(phraseId, phraseDto);
    }

    public DictionaryPhrase findPhraseById(Long phraseId) throws DictionaryPhraseNotFoundException {
        DictionaryPhrase phrase = phraseRepository.findPhraseById(phraseId);
        return phrase;
    }

    public void addPhraseGroup(PhraseGroupDto phraseGroupDto) {
        phraseRepository.addPhraseGroup(phraseGroupDto);
    }
}
