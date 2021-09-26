package org.example.todo.domain.model;


import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class Result<T> implements Serializable {
    

    private int status = 200;
    private String message = "success";

    private T data;

    public Result() {
    }

    public Result(T data) {
        this.data = data;
    }

    public static <T> Result<T> success(T data) {
        return new Result<T>(data);
    }

    public static <T> Result<T> success() {
        return new Result<T>();
    }

    public static <T> Result<T> unKnowErr(String message, Object... args) {
        return Result.error(5000, message, args);
    }


    public static <T> Result<T> error(Integer code, String errMsg, Object... args) {
        for (Object arg : args) {
            errMsg = errMsg.replaceFirst("\\{\\}", Objects.toString(arg));
        }
        return new Result<>(code, errMsg);
    }


    public static <T> Result<T> error(Integer code, String errMsg) {
        return new Result<>(code, errMsg);
    }


    public static <T> Result<T> error(Result result) {
        return new Result<>(result.getStatus(), result.getMessage());
    }

    public Result(int status, String message) {
        this.setStatus(status);
        this.message = message;
    }


    public Result(int status, String message, T data) {
        this.setStatus(status);
        this.message = message;
        this.data = data;
    }
}



