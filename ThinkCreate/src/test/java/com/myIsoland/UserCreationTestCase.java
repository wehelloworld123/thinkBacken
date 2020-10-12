package com.myIsoland;

import com.SpringbootStart;
import com.myIsoland.common.util.SnowflakeIdWorker;
import com.myIsoland.enitity.product.UserCreation;
import com.myIsoland.service.product.UserCreationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootStart.class)
public class UserCreationTestCase {

    @Autowired
    private UserCreationService userCreationService;

    @Test
    public void InsertCreations(){
        List<UserCreation> list = new ArrayList<>();

        for (int i=0;i<50;i++){
            int j = i%3;
            UserCreation data = new UserCreation();
/*            data.setId(new Long(i));*/
            if(j==0) {
                data.setTitle("我是你的一面镜子" + i);
                data.setTyp(j+1);
                data.setContent("王杰自从签约英皇唱片，重出江湖之后，成绩出乎意料的好，几乎每张唱片都有双白金的销售量。在一片不景气之下，仍然有如此佳绩，简直比天王天后还厉害，也难怪英皇老板也送他价值千万港币的豪宅。");
                data.setCreationNm("岳云传"+i);
                data.setCharpNm("精神谋杀"+i);
            }else if(j==1){
                data.setTitle("背叛" + i);
                data.setTyp(j+1);
                data.setPicture("https://ossweb-img.qq.com/images/lol/web201310/skin/big84000.jpg");
                data.setCreationNm("岳云传"+i);
                data.setCharpNm("精神谋杀"+i);
            }else if(j==2){
                data.setTitle("面朝大海，春暖花开" + i);
                data.setTyp(j+1);
                data.setContent("\t\t\t\t从明天起，做一个幸福的人\n" +
                        " \t\t\t\t喂马、劈柴，周游世界\n" +
                        " \t\t\t\t从明天起，关心粮食和蔬菜\n" +
                        " \t\t\t\t我有一所房子，面朝大海，春暖花开 我有一所房子，面朝大海，春暖花开\n" +
                        " \t\t\t\t从明天起，和每一个亲人通信\n" +
                        " \t\t\t\t告诉他们我的幸福\n" +
                        " \t\t\t\t那幸福的闪电告诉我的\n" +
                        " \t\t\t\t我将告诉每一个人\n" +
                        " \t\t\t\t给每一条河每一座山取一个温暖的名字\n" +
                        " \t\t\t\t陌生人，我也为你祝福\n" +
                        " \t\t\t\t愿你有一个灿烂的前程\n" +
                        " \t\t\t\t愿你有情人终成眷属\n" +
                        " \t\t\t\t愿你在尘世获得幸福\n" +
                        " \t\t\t\t我只愿面朝大海，春暖花开");
                data.setCreationNm("满江红"+i);
                data.setCharpNm("我的世界"+i);
            }
                data.setAdopt(1);
            data.setUserId("488294747442511879");
            data.setAvatar("https://ossweb-img.qq.com/images/lol/web201310/skin/big143004.jpg");
            data.setNickname("admin");
            list.add(data);
        }
        userCreationService.saveBatch(list);
        System.out.print("----------------------------");
        System.out.println("XXXXXXX555555xxxxxxxxx:"+userCreationService.GetUserAdoptContent("488294747442511879",new Date(),0,10));


    }
}
