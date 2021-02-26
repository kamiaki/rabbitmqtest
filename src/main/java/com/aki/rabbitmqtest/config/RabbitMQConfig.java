package com.aki.rabbitmqtest.config;


import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  %%%%%   这个配置 在这个例子中没起到作用   %%%%
 *  %%%%%   这个配置 在这个例子中没起到作用   %%%%
 *  %%%%%   这个配置 在这个例子中没起到作用   %%%%
 *  %%%%%   这个配置 在这个例子中没起到作用   %%%%
 *  只有这几个常量字符串被引用
 */
@Configuration
public class RabbitMQConfig {
    static final public String exchangeName = "socket-exchange";
    static final public String queueName = "socket-queue";
    static final public String keyName = "socket.message.key";


    /**
     * Direct模式
     * 注意 这里创建的队列，绑定的默认的交换机 direct。下面的生产者消费者 都没有用到这个
     *
     * @return
     */
    static final private String QUEUE = "direct_queue";

    @Bean
    public Queue directQueue() {
        // 第一个参数是队列名字， 第二个参数是指是否持久化
        return new Queue(QUEUE, true);
    }
}
