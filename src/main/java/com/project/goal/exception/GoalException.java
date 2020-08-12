package com.project.goal.exception;

public class GoalException extends RuntimeException {

    public enum ExceptionType {
        GOAL_ALREADY_EXISTS, TARGET_DOESNOT_EXIT, PLEASE_LOGIN;
    }

    public ExceptionType exceptionType;
    public String message;

    public GoalException(String message, ExceptionType exceptionType) {
        this.exceptionType = exceptionType;
        this.message = message;
    }
}