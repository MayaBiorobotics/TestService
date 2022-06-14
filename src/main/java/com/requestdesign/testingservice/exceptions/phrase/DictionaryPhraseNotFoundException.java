package com.requestdesign.testingservice.exceptions.phrase;

import com.requestdesign.testingservice.entity.phrase.DictionaryPhrase;

public class DictionaryPhraseNotFoundException extends Exception{
    public DictionaryPhraseNotFoundException(String message) {
        super(message);
    }
}
