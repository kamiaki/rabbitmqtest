package com.aki.rabbitmqtest.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Classname RabbitConfig
 * @Date 2021/6/29 14:43
 * @Created by hanzhao
 * 发送时创建交换机
 */
@Component
public class RabbitConfig {
    public static final String topicExchangeName = Config.ALARM_MESSAGE_EXCHANGE_NAME;
    public static final String queueName = Config.ALARM_MESSAGE_QUEUE_NAME1;

    /**
     * 创建队列
     * @return
     */
    @Bean("testQueue")
    Queue testQueue() {
        return new Queue(queueName,false);
    }

    /**
     * 创建信道
     * @return
     */
    @Bean("testExchange")
    TopicExchange testExchange() {
        return new TopicExchange(topicExchangeName);
    }

    @Bean
    Binding binding(@Qualifier(value = "testQueue") Queue testQueue,
                    @Qualifier(value = "testExchange")  TopicExchange testExchange) {
        return BindingBuilder.bind(testQueue).to(testExchange).with("");
    }


//    /**
//     * 绑定队列和他指定的适配器
//     * @param connectionFactory
//     * @param listenerAdapter
//     * @return
//     */
//    @Bean
//    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
//                                             MessageListenerAdapter listenerAdapter) {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.setQueueNames(queueName);
//        container.setMessageListener(listenerAdapter);
//        return container;
//    }

//    /**
//     * 适配器指定接收器和处理方法
//     * @param mereageReceiver
//     * @return
//     */
//    @Bean
//    MessageListenerAdapter listenerAdapter(MessageReceiver mereageReceiver) {
//        return new MessageListenerAdapter(mereageReceiver, "receiveMessage");
//    }
}
