package org.example.todo.common.exception;


import lombok.Data;

/**
 * <h3>jbs-dl-common</h3>
 * 自定义业务异常
 *
 * @author : ck
 * @date : 2021-06-22 11:16
 **/
@Data
public class BusinessException extends RuntimeException {
    private int code;
    private String mes;
    private Object data;
    public BusinessException(int code, String mes) {
        this.code = code;
        this.mes = mes;
    }

    public BusinessException(int code, String mes, Object data) {
        this.code = code;
        this.mes = mes;
        this.data = data;
    }
    public BusinessException(ExceptionEnums exceptionEnums) {
        this.code = exceptionEnums.getCode();
        this.mes = exceptionEnums.getDefaultMessage();
    }

}
