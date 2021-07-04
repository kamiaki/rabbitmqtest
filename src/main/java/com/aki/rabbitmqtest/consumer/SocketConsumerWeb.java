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
 * 这个例子 是topic 模式 匹配keyname的模式 ，自动根据名字创建 queue
 */
@Component
public class SocketConsumerWeb {

    @RabbitListener(bindings = @QueueBinding(
            // value 是队列名
            // durable 是队列持久化
            // autoDelete是队列不是临时队列 这样消息就是持久化
            value = @Queue(value = RabbitMQConfig.queueNameWeb,
                    durable = "true",
                    autoDelete = "false"),
            // value 交换机名字
            // durable 交换机持久化 默认为持久
            // type 交换机类型 Direct
            //
            //  1.Direct模式
            //消息中的路由键（routing key）如果和 Binding 中的 binding key 一致， 交换器就将消息发到对应的队列中。路由键与队列名完全匹配
            //2.Topic模式
            //topic 交换器通过模式匹配分配消息的路由键属性，将路由键和某个模式进行匹配，此时队列需要绑定到一个模式上。它将路由键和绑定键的字符串切分成单词，这些单词之间用点隔开。它同样也会识别两个通配符：符号“#”和符号“*”。#匹配0个或多个单词，*匹配一个单词。
            //3.Fanout模式
            //每个发到 fanout 类型交换器的消息都会分到所有绑定的队列上去。fanout 交换器不处理路由键，只是简单的将队列绑定到交换器上，每个发送到交换器的消息都会被转发到与该交换器绑定的所有队列上。很像子网广播，每台子网内的主机都获得了一份复制的消息。fanout 类型转发消息是最快的。
            //
            //     public static final String DIRECT = "direct";    路由模式
            //    public static final String TOPIC = "topic";   动态路由
            //    public static final String FANOUT = "fanout";  广播
            //    public static final String HEADERS = "headers";
            //    public static final String SYSTEM = "system";
            exchange = @Exchange(value = RabbitMQConfig.exchangeName,
                    durable = "true",
                    type = ExchangeTypes.TOPIC),
            //   路由key
            key = RabbitMQConfig.keyNameWeb))
    @RabbitHandler
    public void receiveSocket(@Payload SocketMessage socketMessage, @Headers Map<String, Object> headers,
                              Channel channel) throws Exception {
        System.out.println("-----------接收到消息 SocketConsumerWeb--------");
        System.out.println("SocketConsumerWeb 消息内容：" + socketMessage.toString());
        //消息确认 ACK 有这个如果没有消费了 不会删除消息
        // 参数一 消息标识  参数二 是否批量确认
        // 配置文件 修改## 签收方式
        //spring.rabbitmq.listener.simple.acknowledge-mode=manual
        boolean isInsert = true;
//        boolean isInsert = true;
        if (isInsert){
            channel.basicAck((Long) headers.get(AmqpHeaders.DELIVERY_TAG), false);
        }else{
            throw new RuntimeException("没有存入成功!");
        }
    }
}
