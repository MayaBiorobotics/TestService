package com.requestdesign.testingservice.exceptions.auth;

public class LoginIsUserException extends Exception{
    public LoginIsUserException(String message) {
        super(message);
    }
}
