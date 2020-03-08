package com.myIsoland.domain;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RabbitListener(queues = "hello")
public class HelloReceiver {
    @RabbitHandler
    public void process(String hello){
        System.out.println("Receiver:" + hello);
    }
}
