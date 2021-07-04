package com.aki.rabbitmqtest.controller;

import com.aki.rabbitmqtest.pojo.SocketMessage;
import com.aki.rabbitmqtest.producer.SoketProducer;
import com.aki.rabbitmqtest.producer.SoketProducerWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class SendMessgeController {
    @Autowired
    private SoketProducer soketProducer;
    @Autowired
    private SoketProducerWeb soketProducerWeb;
    @GetMapping("sendSocket")
    public SocketMessage sendSocket(@RequestParam String content) throws Exception{
        SocketMessage socketMessage = new SocketMessage();
        socketMessage.setId("testid");
        socketMessage.setSenderUser("admin");
        socketMessage.setReceiverUser("aki");
        socketMessage.setSendTime(new Date());
        socketMessage.setContent(content);
        soketProducer.send(socketMessage);
        soketProducerWeb.send(socketMessage);
        return socketMessage;
    }
}
