package com.luo.order.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.luo.order.dataobject.OrderDetail;
import com.luo.order.dto.OrderDTO;
import com.luo.order.enums.ResultEnum;
import com.luo.order.exception.OrderException;
import com.luo.order.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
@Slf4j
public class OrderForm2OrderDTOConverter {

    public static OrderDTO convert(OrderForm orderForm) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());

        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        Gson gson = new Gson();
        try{

            orderDetailList= gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>() {
                    }.getType());
        }catch(Exception e){
            log.error("json字符串转换出错，string={}",orderForm.getItems());
            throw new OrderException(ResultEnum.PARAM_ERROR);
        }

        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;


    }
}
