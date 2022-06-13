package com.requestdesign.testingservice.exceptions.test;

public class TaskNotFoundException extends Exception{
    public TaskNotFoundException(String message) {
        super(message);
    }
}
