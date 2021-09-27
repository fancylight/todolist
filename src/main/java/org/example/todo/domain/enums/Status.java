package org.example.todo.domain.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum Status {
    COMPLETED(0, "已完成"),
    UN_COMPLETED(1, "未完成");
    @EnumValue
    private int code;
    private String mes;

    Status(int code, String mes) {
        this.code = code;
        this.mes = mes;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }
}
