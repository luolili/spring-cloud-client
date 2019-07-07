package com.luo.order.service.impl;

import com.luo.order.client.ProductClient;
import com.luo.order.dataobject.OrderDetail;
import com.luo.order.dataobject.OrderMaster;
import com.luo.order.dataobject.ProductInfo;
import com.luo.order.dto.CartDTO;
import com.luo.order.dto.OrderDTO;
import com.luo.order.enums.OrderStatusEnum;
import com.luo.order.enums.PayStatusEnum;
import com.luo.order.repo.OrderDetailRepository;
import com.luo.order.repo.OrderMasterRepository;
import com.luo.order.service.OrderService;
import com.luo.order.util.KeyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ProductClient productClient;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
       String orderId = KeyUtils.genUniqueKey();
        //-1v调用商品服务
        List<String> productIdList = orderDTO.getOrderDetailList().stream()
                .map(OrderDetail::getProductId)
                .collect(Collectors.toList());
        List<ProductInfo> productInfoList = productClient.listForOrder(productIdList);
        //-2 计算总价
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getProductId().equals(orderDetail.getProductId())) {
                orderAmount = productInfo.getProductPrice()
                        .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                .add(orderAmount);//累加
                    BeanUtils.copyProperties(productInfo, orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtils.genUniqueKey());
                    //订单详情入库
                    orderDetailRepository.save(orderDetail);
                }

            }
        }

        //-3 扣库存

        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productClient.decreaseStock(cartDTOList);

        orderDTO.setOrderId(orderId);
        //订单入库
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);

        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }
}
