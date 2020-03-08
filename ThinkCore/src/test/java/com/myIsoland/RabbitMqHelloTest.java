package com.myIsoland;


import com.SpringbootStart;
import com.myIsoland.common.component.MailSender;
import com.myIsoland.enitity.email.MailSenderInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootStart.class)
public class RabbitMqHelloTest{
    @Autowired
    private MailSender mailSender;

    @Test
    public void testSend(){
        MailSenderInfo email = new MailSenderInfo();
        email.setToAddress("1131095930@qq.com");
        email.setSubject("hello");
        email.setContent("全军出击");
        email.setFromAddress("1131095930@qq.com");
        email.setUserName("半生缘");
        email.setPassword("xy1131095930.");
        email.setValidate(false);
        mailSender.send(email);
    }
}
