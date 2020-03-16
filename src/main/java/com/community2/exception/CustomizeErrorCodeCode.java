package com.community2.exception;

public enum CustomizeErrorCodeCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND(2001,"你找的问题不在了，换一个试试？"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题或评论"),
    NO_LOGIN(2003,"请先登录，再重试"),
    SYSTEM_ERROR(2004,"服务器冒烟了，请稍后尝试~"),
    TYPE_PARAM_ERROR(2005,"评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006,"抱歉，你回复的评论不存在"),
    COMMENT_IS_EMPTY(2007,"评论不能为空")

    ;
    private String message;
    private Integer code;

    CustomizeErrorCodeCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

}
