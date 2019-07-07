package com.luo.order.controller;

import com.luo.order.exception.OrderException;
import com.luo.order.form.OrderForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @PostMapping("/create")
    public void create(@Validated OrderForm orderForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.error("创建订单，参数不正确，orderForm={}", orderForm);
            throw new OrderException(1, bindingResult.getFieldError().getDefaultMessage());
        }

    }
}
