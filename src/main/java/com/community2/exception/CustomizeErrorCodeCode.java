package com.community2.exception;

public enum CustomizeErrorCodeCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND("你找的问题不在了，换一个试试？");
    private String message;

    CustomizeErrorCodeCode(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
