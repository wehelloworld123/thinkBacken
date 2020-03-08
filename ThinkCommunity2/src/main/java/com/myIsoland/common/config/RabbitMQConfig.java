package com.myIsoland.common.config;

import com.myIsoland.common.constant.RabbitConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue Queue() {
        return new Queue("mail-queue",true);
    }
    @Bean
    public Queue hello(){
        return new Queue("hello");
    }
    /**
     * 声明交换机
     *
     * @return
     */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("mail-exchange");
    }

    /**
     * 绑定
     *
     * @return
     */
    @Bean
    public Binding bindingEmail() {
        return BindingBuilder.bind(Queue()).to(topicExchange()).with(RabbitConstant.EMAIL_ROUTING_KEY);//*表示一个词,#表示零个或多个词
    }


}