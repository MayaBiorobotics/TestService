package com.requestdesign.testingservice.exceptions.test;

import org.springframework.data.crossstore.ChangeSetPersister;

public class QuestionNotFoundException extends Exception {
    public QuestionNotFoundException(String message) {
        super(message);
    }
}
