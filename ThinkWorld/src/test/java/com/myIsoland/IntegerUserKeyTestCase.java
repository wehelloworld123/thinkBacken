package com.myIsoland;

import com.myIsoland.component.IntegerComponent;
import com.myIsoland.constant.ProjectConstant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IntegerUserKeyTestCase {


    @Test
    public void test(){
       System.out.print("XXXXXXXXXX:"+IntegerComponent.generateAppKey(ProjectConstant.USERPREFIX,"2THINK838213431"));

    }
}
