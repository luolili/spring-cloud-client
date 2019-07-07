package com.luo.product.exception;

import com.luo.product.enums.ResultEnum;
import lombok.Data;

public class ProductException extends RuntimeException{

    private Integer code;

    public ProductException(Integer code, String message) {
        super(message);
        this.code = code;
    }
    public ProductException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }


}
