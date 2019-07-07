package com.luo.order.client;

import com.luo.order.dataobject.ProductInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * 这是一个interface
 */
@FeignClient(name = "product")
public interface ProductClient {

    //product服务下面的接口
    @GetMapping("/msg")
    String productMsg();

    //商品列表的接口
     @PostMapping("/product/listForOrder")
     List<ProductInfo> listForOrder(List<String> productIdList);


}
