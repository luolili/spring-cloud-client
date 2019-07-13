package com.luo.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@EnableBinding(StreamClient.class)
public class StreamReceiver {

    /**
     * log:
     * stream receiver: now:Sat Jul 13 21:08:19 CST 2019
     * @param message
     */
    @StreamListener("myMessage01")
    public void process(Object message) {
        log.info("stream receiver: {}", message);
    }
}
