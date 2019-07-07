package com.luo.product.service;

import com.luo.product.dataobject.ProductCategory;
import com.luo.product.dataobject.ProductInfo;

import java.util.List;

public interface CategoryService {

    /**
     * 查询所有的类目
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

}
