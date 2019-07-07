package com.luo.order.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
@Data
public class OrderForm {

    @NotEmpty(message = "姓名必填")//javax
    private String name;

    @NotEmpty(message = "手机号必填")//javax
    private String phone;

    @NotEmpty(message = "地址必填")//javax
    private String address;

    @NotEmpty(message = "微信openid不能为空")//javax
    private String openid;

    @NotEmpty(message = "购物车不能为空")//javax
    private String items;



}
