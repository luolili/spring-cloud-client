package com.luo.apigateway;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.stereotype.Component;

/**
 * 动态配置：
 * 让当你在github上修改了config-repo里面的文件时
 * 同步更新到代码里面的 yml配置文件
 */
@Component
public class ZuulConfig {

    @ConfigurationProperties("zuul")
    public ZuulProperties zuulProperties() {
        return new ZuulProperties();
    }
}
