package com.luo.order.repo;

import com.luo.order.dataobject.OrderMaster;
import com.luo.order.enums.OrderStatusEnum;
import com.luo.order.enums.PayStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Test
    public void test() {
        OrderMaster orderMaster = new OrderMaster();

        orderMaster.setOrderId("1234567");
        orderMaster.setBuyerName("是想");
        orderMaster.setBuyerPhone("2321");
        orderMaster.setBuyerAddress("hre");
        orderMaster.setBuyerOpenid("112");
        orderMaster.setOrderAmount(new BigDecimal(2.5));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster.setCreateTime(new Date());
        orderMaster.setUpdateTime(new Date());
        OrderMaster re = orderMasterRepository.save(orderMaster);


    }

}