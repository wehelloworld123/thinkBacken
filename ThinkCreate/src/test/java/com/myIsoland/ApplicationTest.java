package com.myIsoland;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {
    @Test
    public void testSend(){
        BigDecimal bdv = new BigDecimal(0);

        //bdv = bdv.setScale(8,BigDecimal.ROUND_HALF_UP);
        System.out.println(bdv);
    }
}
