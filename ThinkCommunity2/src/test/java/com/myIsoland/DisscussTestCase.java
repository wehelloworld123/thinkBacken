package com.myIsoland;

import cn.hutool.core.date.DateTime;
import com.alibaba.fastjson.JSONObject;
import com.myIsoland.enitity.community.Disscuss;
import com.myIsoland.service.community.DisscussService;
import com.myIsoland.util.StringUtils;
import org.apache.tomcat.jni.Time;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DisscussTestCase {
    @Autowired
    private DisscussService disscussService;
    @Test
    public void testSend(){
        List<Disscuss> data = new ArrayList<>();
        data.add(new Disscuss(Long.parseLong("1314"),10,"xuyong"));
        data.add(new Disscuss(Long.parseLong("13234"),3,"xuyong21"));
        data.add(new Disscuss(Long.parseLong("65765"),7,"xuyong111"));
        disscussService.batchUpdateLikes(data);
    }
    @Test
    public void test(){
        String a = "pre_12345678-abcdrfg";
        String sub = a.substring(4);
        System.out.println(sub);
        int h = sub.indexOf("-");
        System.out.println(sub.substring(0,h));
        System.out.println(sub.substring(0,h+1));
        System.out.println(sub.substring(h));
        System.out.println(sub.substring(h+1));
        System.out.println(sub.substring(h+2));
    }
    @Test
    public void test2(){
        String a = "12345678;abcdrfg;23131231;ass2e4fr;sae3rf4;";
    }
}
