package com.luo.order.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {


    PARAM_ERROR(1,"参数错误"),
    ;
    private Integer code;
    private String msg;
     ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

