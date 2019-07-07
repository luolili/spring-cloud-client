package com.luo.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 调用端：client
 * 1. 用restTemplate
 */
@RestController
@Slf4j
public class ClientController {

    //第二种方法：
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @GetMapping("/getProductMsg")
    public String getProductMsg() {
        RestTemplate restTemplate = new RestTemplate();
        //缺点是：必须知道server端的ip
        String res = restTemplate.getForObject("http://localhost:8080/msg", String.class);

        log.info("msg: {}",res);
        return res;
    }

    @GetMapping("/getProductMsg01")
    public String getProductMsg01() {
        ServiceInstance product = loadBalancerClient.choose("PRODUCT");
        String host = product.getHost();
        int port = product.getPort();
        String url = String.format("http://%s:%s", host, port) + "/msg";
        RestTemplate restTemplate = new RestTemplate();
        //缺点是：必须知道server端的ip
        String res = restTemplate.getForObject(url, String.class);

        log.info("msg: {}",res);
        return res;

    }

    //------第三种方法:先加配置类
    @Autowired
    private RestTemplate restTemplate;
    @GetMapping("/getProductMsg02")
    public String getProductMsg02() {
        String res = restTemplate.getForObject("http://PRODUCT/msg", String.class);
        log.info("msg: {}",res);
        return res;
    }

}
