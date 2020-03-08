package com.myIsoland.common.component;


import com.myIsoland.enitity.email.MailSenderInfo;
import com.myIsoland.common.util.SimpleEmailUtil;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;

import java.util.Map;

@Component
public class MailConsummer {

    @Qualifier(value = "emailUtil")
    private SimpleEmailUtil simpleEmailUtil;
    @Qualifier
    private TemplateEngine templateEngine;

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "mail-queue2", durable = "true"), exchange = @Exchange(name = "mail-exchange2", durable = "true", type = "topic"), key = "mail.*"))
    @RabbitHandler
    public void onMailMessage(@Payload MailSenderInfo email, @Header Map<String,Object> headers, Channel channel) throws Exception{
        System.out.println("-----------收到消息------------");
//        Context context = new Context();
//        context.setVariable("id",email.getContent());
//        String emailContent = templateEngine.process("emailTemplate",context);
        simpleEmailUtil.sendTextMail(email);
        Long d = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);

        channel.basicAck(d, false);
    }
}
