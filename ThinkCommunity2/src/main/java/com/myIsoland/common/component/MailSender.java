package com.myIsoland.common.component;

import com.myIsoland.enitity.email.MailSenderInfo;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MailSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(MailSenderInfo email){
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId("123456");
        rabbitTemplate.convertAndSend("mail-exchange",
                "mail.text",email,correlationData);
    }
}
