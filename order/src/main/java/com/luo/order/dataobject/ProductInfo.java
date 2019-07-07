package com.luo.order.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
public class ProductInfo {

    @Id
    //@GeneratedValue
    private String productId;

    //商品名称
    private String productName;

    //价格
    private BigDecimal productPrice;

    //库存
    private Integer productStock;

    //描述
    private String productDescription;

    //小图
    private String productIcon;

    //商品状态： 1： 下架， 0： 正常
    private Integer productStatus;

    //商品类型
    private Integer categoryType;

    private Date createTime;
    private Date updateTime;



}
