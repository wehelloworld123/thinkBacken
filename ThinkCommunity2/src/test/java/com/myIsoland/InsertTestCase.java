package com.myIsoland;

import com.myIsoland.enitity.community.UserCorportion;
import com.myIsoland.service.community.UserCorpService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InsertTestCase {
    @Autowired
    private UserCorpService userCorpService;
    @Test
    public void testSend(){
        UserCorportion data = new UserCorportion();
        data.setCid("11111");
        data.setUid("2222");
        System.out.println("SSSSSSS:"+data.getCreateBy());
        System.out.println("SSSSSSS:"+data.getCreateDat());
        System.out.println("SSSSSSS:"+data.getLUpdateDat());
        userCorpService.SaveUserCorp(data);
    }
}
