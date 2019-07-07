package com.luo.product.service.impl;

import com.luo.product.dataobject.ProductInfo;
import com.luo.product.enums.ProductStatusEnum;
import com.luo.product.repo.ProductInfoRepository;
import com.luo.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }
}
