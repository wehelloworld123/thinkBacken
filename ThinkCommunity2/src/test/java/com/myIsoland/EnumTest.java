package com.myIsoland;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EnumTest {
    @Test
    public void test(){
        EntityTest entityTest = new EntityTest();
        System.out.println(entityTest.getAmt());
        if(entityTest.getAmt()!=null){
            System.out.println("false");
        }else{
            System.out.println("true");
        }
    }
}
