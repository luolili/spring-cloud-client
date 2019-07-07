package com.luo.order.client;

import com.luo.order.dataobject.ProductInfo;
import com.luo.order.dto.CartDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
     List<ProductInfo> listForOrder(@RequestBody List<String> productIdList);

     //减少库存
    @PostMapping("/product/decreaseStock")//因为你用了@RequestBody， 这里要用PostMapping
    void decreaseStock(@RequestBody List<CartDTO> cartDTOList) ;

}
