package com.myIsoland;

import com.SpringbootStart;
import com.myIsoland.common.util.CaculateUtils;
import com.myIsoland.common.util.DateUtils;
import com.myIsoland.enitity.debate.Answer;
import com.myIsoland.service.debate.UserTopicService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootStart.class)
public class StringSubTest {
    @Autowired
    private UserTopicService userTopicService;
    @Test
    public void test(){
        String pre = "THINK";
        String no ="THINK12345";
        System.out.println(CaculateUtils.translateId(pre,no));
    }

    @Test
    public void testDate(){
        System.out.println("show:"+DateUtils.getNowDate());
    }

    @Test
    public void testMap(){
        System.out.println("mapShow:"+userTopicService.GetUserRankAns());
    }
}
