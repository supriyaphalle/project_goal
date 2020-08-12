package com.project.goal.exception;

public class JwtTokenException extends RuntimeException {
    public enum ExceptionType {
        EMPTY_TOKEN;
    }

    public ExceptionType exceptionType;
    public String message;

    public JwtTokenException(String message, ExceptionType exceptionType) {
        this.exceptionType = exceptionType;
        this.message = message;
    }
}
