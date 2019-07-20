package com.luo.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
//@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixController {

    @HystrixCommand(fallbackMethod = "fallback", commandProperties = {
        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value="6000")
    })
    @GetMapping("/getProductInfoList")
    public String getProductInfoList() {
        RestTemplate restTemplate = new RestTemplate();
       return restTemplate.postForObject("http://127.0.0.1:8082/product/listForOrder",
                Arrays.asList("157875196366160022"), String.class);


    }
    //服务降级的回调方法：出错的时候调用这个方法，抛出异常的时候也会执行他
    public String fallback() {
        return "too busy";
    }

    public String defaultFallback() {
        return "de too busy";
    }
}
