package com.luo.product.service;

import com.luo.product.dataobject.ProductInfo;

import java.util.List;

public interface ProductService {

    /**
     * 查询所有的上架商品
     * @return
     */
    List<ProductInfo> findUpAll();
    List<ProductInfo> findList(List<String> productIdList);


}
