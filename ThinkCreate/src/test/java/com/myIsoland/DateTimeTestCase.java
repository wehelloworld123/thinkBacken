package com.myIsoland;

import cn.hutool.core.date.DateTime;
import com.myIsoland.common.util.CaculateUtils;
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
        System.out.println();
    }

    @Test
    public void test(){
        Integer start = 0;
        Integer limit = 10;
        System.out.println(start*limit);
    }
}
