package com.luo.order.service;

import com.luo.order.dataobject.OrderMaster;
import com.luo.order.dto.OrderDTO;

public interface OrderService {

    OrderDTO  create(OrderDTO orderDTO);

    //完结订单
    OrderDTO finish(String orderId);
}
