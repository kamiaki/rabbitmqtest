package com.aki.rabbitmqtest.config;


import org.springframework.amqp.core.FanoutExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author ：daniel
 * @Date ：Created in 2021/6/25 10:00
 * @Description ：配置
 */

/**
 * 接收时创建交换机
 */
@Configuration
public class RabbitAutoConfiguration {
    /**
     * 定义个proto雷电数据交换器
     * @return
     */
    @Bean
    public FanoutExchange protoThunderExchange() {
        return new FanoutExchange(Config.PROTO_THUNDER_EXCHANGE_NAME,true,false);
    }

    /**
     * 定义个proto雷电概率格点交换器
     * @return
     */
    @Bean
    public FanoutExchange protoGridExchange() {
        return new FanoutExchange(Config.PROB_GRID_EXCHANGE_NAME,true,false);
    }

    /**
     * 定义个alarmGrid交换器 用于生产预警格点数据
     * @return
     */
    @Bean
    public FanoutExchange alarmGridExchange() {
        return new FanoutExchange(Config.ALARM_GRID_EXCHANGE_NAME);
    }

    /**
     * 定义个alarmGrid交换器 用于生产预警格点数据
     * @return
     */
    @Bean
    public FanoutExchange alarmMsgExchange() {
        return new FanoutExchange(Config.ALARM_MESSAGE_EXCHANGE_NAME);
    }

}
