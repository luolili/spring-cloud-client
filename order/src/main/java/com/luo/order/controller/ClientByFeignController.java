package com.luo.order.controller;

import com.luo.order.client.ProductClient;
import com.luo.order.dataobject.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * 调用端：client
 * 1. 用restTemplate
 */
@RestController
@Slf4j
public class ClientByFeignController {

    @Autowired
    private ProductClient productClient;

    @GetMapping("/getProductMsg03")
    public String getProductMsg() {
        String response = productClient.productMsg();
        log.info("response={}", response);
        return response;

    }

    //test接口是否通
    @GetMapping("/getProductList")
    public String getProductList() {
        List<ProductInfo> productInfos =
                productClient.listForOrder(Arrays.asList("157875196366160022", "157875227953464068"));
        log.info("response={}", productInfos);
        return "od";

    }




}