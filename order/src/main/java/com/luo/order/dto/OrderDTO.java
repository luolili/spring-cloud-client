package com.luo.order.dto;

import com.luo.order.dataobject.OrderDetail;
import com.luo.order.dataobject.OrderMaster;
import lombok.Data;

import java.util.List;
@Data
public class OrderDTO extends OrderMaster {

    List<OrderDetail> orderDetailList;
}
