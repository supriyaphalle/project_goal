package com.project.goal.exception;

public class UserException extends Throwable {

    public enum ExceptionType {
        USER_ALREADY_EXIST, INVALID_PASSWORD, INVALID_EMAIL_ID;
    }

    public ExceptionType exceptionType;
    public String message;

    public UserException(String message, ExceptionType exceptionType) {
        this.exceptionType = exceptionType;
        this.message = message;
    }
}
