package com.myIsoland;

import com.SpringbootStart;
import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.enitity.community.Corporation;
import com.myIsoland.util.JsonMsgUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootStart.class)
public class SuccessMsgTestCase {

    @Test
    public void contextLoads() {
        Corporation data =new Corporation();
        data.setContribution("湖北省共青团省委");
        data.setDescription("社团");
        data.setId("18371864");
        data.setLike(30);
        data.setLogo("https://www");
        System.out.println(AjaxResult.success(data.toString()));
    }
}