package com.luo.product.service;

import com.luo.product.ProductApplicationTests;
import com.luo.product.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProductServiceTest   {

    @Autowired
    private  ProductService productService;
    @Test
    public void test() {
        List<ProductInfo> list = productService.findUpAll();
        Assert.assertTrue(list.size() > 0);


    }

}