package com.myIsoland;

import com.myIsoland.enums.CreateKind;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.SpringVersion;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
public class EnumTest {

    public static void main(String[] args){
        BigDecimal d = new BigDecimal("1213");
        Object a= "12";
        String ab="111";
        Integer abc = 1;
        System.out.println("1:"+(String)a);
        System.out.println("2:"+(String)ab);

        System.out.println("3:"+a.toString());
        System.out.println("4:"+ab.toString());
        if(abc instanceof Integer) {
            if(abc!=null) {
                System.out.println("5:" + abc.toString());
            }
        }
        System.out.println("6:"+d.toString());
    }


    @Test
    public void test2(){
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("a","1");
        list.add(map);
        for(Map<String,Object> m:list){
            System.out.println(m.get("b"));
        }
    }

        @Test
        public void Test1(){
            String version = SpringVersion.getVersion();
            String version1 = SpringBootVersion.getVersion();
            System.out.println("version:"+version);
            System.out.println("version1:"+version1);
        }


}
