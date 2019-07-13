package com.luo.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 接受消息
 * log: 你要点开5:debug才可以看到这个
 * 2019-07-13 17:41:54.960  INFO 6312 --- [ntContainer#0-1]
 * com.luo.order.message.MqReceiver         : msg: nowSat Jul 13 17:41:54 CST 2019
 */
@Component
@Slf4j
public class MqReceiver {

    //RabbitListener(queues = "myQueue")//这个方法不会自动在rabbitmq的管理页面上创建一个叫myQueue的队列
    @RabbitListener(queuesToDeclare = @Queue("myQueue"))
    public void process(String message) {
        log.info("msg: {}",message);
    }
}
