package com.luo.order.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * 将restTemplate用配置类的方式获取
 */
@Component
public class RestTemplateConfig {

    @Bean//将RestTemplate作为spring的bean，这样就可以自动注入他了
    @LoadBalanced//from cloud client
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
