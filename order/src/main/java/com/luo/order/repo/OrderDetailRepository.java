package com.luo.order.repo;

import com.luo.order.dataobject.OrderDetail;
import com.luo.order.dataobject.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {
}
