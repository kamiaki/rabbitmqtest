package com.aki.rabbitmqtest.producer;

import com.aki.rabbitmqtest.config.RabbitMQConfig;
import com.aki.rabbitmqtest.pojo.SocketMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: 消息生产者
 */
@Component
public class SoketProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(SocketMessage socketMessage) throws Exception {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.exchangeName, //分发消息的交换机名称
                RabbitMQConfig.keyName,  //用来匹配消息队列的Key
                socketMessage,       //消息体
                new CorrelationData(socketMessage.getId())//消息id
        );

    }
}
