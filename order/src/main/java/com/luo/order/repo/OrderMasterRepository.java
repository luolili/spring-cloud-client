package com.luo.order.repo;

import com.luo.order.dataobject.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {
}
