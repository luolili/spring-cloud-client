package com.luo.order.service.impl;

import com.luo.order.dataobject.OrderMaster;
import com.luo.order.dto.OrderDTO;
import com.luo.order.enums.OrderStatusEnum;
import com.luo.order.enums.PayStatusEnum;
import com.luo.order.repo.OrderMasterRepository;
import com.luo.order.service.OrderService;
import com.luo.order.util.KeyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        orderDTO.setOrderId(KeyUtils.genUniqueKey());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);

        orderMaster.setOrderAmount(new BigDecimal(33.3));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }
}
