package com.aki.rabbitmqtest.consumer;

import com.aki.rabbitmqtest.config.RabbitMQConfig;
import com.aki.rabbitmqtest.pojo.SocketMessage;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 * @Description: 消息消费端
 */
@Component
public class SocketConsumer {

    @RabbitListener(bindings = @QueueBinding(
            // value 是队列名
            // durable 是队列持久化
            // autoDelete是队列不是临时队列 这样消息就是持久化
            value = @Queue(value = RabbitMQConfig.queueName, durable = "true", autoDelete = "false"),
            // value 交换机名字
            // durable 交换机持久化 默认为持久
            // type 交换机类型 Direct
            //     public static final String DIRECT = "direct";    路由模式
            //    public static final String TOPIC = "topic";   动态路由
            //    public static final String FANOUT = "fanout";  广播
            //    public static final String HEADERS = "headers";
            //    public static final String SYSTEM = "system";
            exchange = @Exchange(value = RabbitMQConfig.exchangeName, durable = "true", type = ExchangeTypes.TOPIC),
            //   路由key
            key = RabbitMQConfig.keyName))
    @RabbitHandler
    public void receiveSocket(@Payload SocketMessage socketMessage, @Headers Map<String, Object> headers,
                              Channel channel) throws Exception {
        System.out.println("-----------接收到消息--------");
        System.out.println("消息内容：" + socketMessage.toString());
        //消息确认 ACK 有这个如果没有消费了 不会删除消息
        // 参数一 消息标识  参数二 是否批量确认
        // 配置文件 修改## 签收方式
        //spring.rabbitmq.listener.simple.acknowledge-mode=manual
        channel.basicAck((Long) headers.get(AmqpHeaders.DELIVERY_TAG), false);
    }
}
