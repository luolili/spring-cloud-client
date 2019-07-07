package com.luo.order.repo;

import com.luo.order.dataobject.OrderDetail;
import com.luo.order.dataobject.OrderMaster;
import com.luo.order.enums.OrderStatusEnum;
import com.luo.order.enums.PayStatusEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void test() {

        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setDetailId("12345");
        orderDetail.setOrderId("1234567");
        orderDetail.setProductId("12");
        orderDetail.setProductName("hu");
        orderDetail.setProductPrice(new BigDecimal(2.5));
        orderDetail.setProductQuantity(22);
        orderDetail.setProductIcon("//http:/ret");
        OrderDetail re = orderDetailRepository.save(orderDetail);


    }

}