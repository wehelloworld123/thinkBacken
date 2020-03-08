package com.myIsoland;

import cn.hutool.core.date.DateTime;
import org.apache.tomcat.jni.Time;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DateTimeTestCase {

    @Test
    public void testSend(){
        System.out.println("test start");
        System.out.println(new Date());
        System.out.println(new Time());
        System.out.println(new DateTime());
    }
}
