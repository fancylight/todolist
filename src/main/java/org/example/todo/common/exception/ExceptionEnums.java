package org.example.todo.common.exception;

public enum ExceptionEnums {
    NOT_EXIST_DATA(10001,"不存在的数据"),
    PASSWORD_ERROR(10002,"错误的密码"),
    TOKEN_EXPIRED(10003,"过期的token"),
    USER_EXPIRED(10004,"用户已离线"),
    USER_NOT_LOGIN(10005,"请登录");
    private int code;
    private String defaultMessage;

    ExceptionEnums(int code, String defaultMessage) {
        this.code = code;
        this.defaultMessage = defaultMessage;
    }

    public int getCode() {
        return code;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }
}
