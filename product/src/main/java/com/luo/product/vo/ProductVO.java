package com.luo.product.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ProductVO {

    //这个注解是用后台的属性categoryName与返回的response里面的data里面的属性name对应
    @JsonProperty("name")
    private String categoryName;
    @JsonProperty("type")
    private Integer categoryType;
    @JsonProperty("foods")
    List<ProductInfoVO> productInfoVOList;
}
