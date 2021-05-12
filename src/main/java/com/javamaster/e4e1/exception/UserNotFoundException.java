package com.javamaster.e4e1.exception;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(String errorMessage) {
        super(errorMessage);
    }

}
