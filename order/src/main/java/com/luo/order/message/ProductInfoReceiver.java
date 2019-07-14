package com.luo.order.message;

import com.luo.order.dataobject.ProductInfo;
import com.luo.order.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProductInfoReceiver {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private static final String PRODUCT_STOCK_TEMPLATE ="product_stock_%s";
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myOrder"),
            key = "computer",
            value = @Queue("computerOrder")
    ))
    public void process02(String message) {

        ProductInfo o = (ProductInfo) JsonUtils.fromJson(message, ProductInfo.class);
        log.info("productInfo: {}",o);
        //存入redis
        stringRedisTemplate.opsForValue().set(String.format(PRODUCT_STOCK_TEMPLATE,o.getProductId()),
                String.valueOf(o.getProductStock()));
    }

}
