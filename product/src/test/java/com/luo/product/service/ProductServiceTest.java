package com.luo.product.service;

import com.luo.product.ProductApplicationTests;
import com.luo.product.dataobject.ProductInfo;
import com.luo.product.dto.CartDTO;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
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

    @Test
    public void testFindList() {
        List<ProductInfo> list = productService
                .findList(Arrays.asList("157875196366160022","157875227953464068"));
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void testDecreaseStock() {
        List<ProductInfo> list = productService
                .findList(Arrays.asList("157875196366160022","157875227953464068"));

        CartDTO cartDTO = new CartDTO();
        cartDTO.setProductId("157875196366160022");
        cartDTO.setProductQuantity(2);
        productService.decreaseStock(Arrays.asList(cartDTO));


        Assert.assertTrue(list.size() > 0);
    }

}