package com.luo.order.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试是否拿到了所有的配置文件
 */
@RestController
@RequestMapping("/env")
public class EnvController {

   /* @Value("${env}")
    private String env;

    @GetMapping("print")
    public String printEnv() {
        return env;
    }
*/
}
