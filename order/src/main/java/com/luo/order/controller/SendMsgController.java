package com.luo.order.controller;

import com.luo.order.message.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class SendMsgController {

    @Autowired
    private StreamClient streamClient;

    @GetMapping("/send")
    public void process() {
        streamClient.output()
                .send(MessageBuilder.withPayload("now:" + new Date()).build());

    }
}
