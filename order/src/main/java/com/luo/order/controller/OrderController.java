package com.luo.order.controller;

import com.luo.order.client.ProductClient;
import com.luo.order.converter.OrderForm2OrderDTOConverter;
import com.luo.order.dto.OrderDTO;
import com.luo.order.enums.ResultEnum;
import com.luo.order.exception.OrderException;
import com.luo.order.form.OrderForm;
import com.luo.order.service.OrderService;
import com.luo.order.util.ResultVOUtils;
import com.luo.order.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;


    /**
     * 查询商品信息，需要调用商品的服务
     */
    @PostMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        //-1 参数验证
        if (bindingResult.hasErrors()) {
            log.error("创建订单，参数不正确，orderForm={}", orderForm);
            throw new OrderException(1, bindingResult.getFieldError().getDefaultMessage());
        }
        //-2 form--->dto
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("购物车为空");
            throw new OrderException(ResultEnum.CART_EMPTY);
        }

        OrderDTO result = orderService.create(orderDTO);

        Map<String, String> map = new HashMap<>();

        map.put("orderId", result.getOrderId());
        return ResultVOUtils.success(map);
    }
}
