package com.luo.user.vo;

import lombok.Data;

@Data
public class ResultVO<T> {

    //错误码
    private Integer code;

    //提示信息
    private String msg;

    //响应的数据
    private T data;

}
