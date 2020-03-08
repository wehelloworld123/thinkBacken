package com.myIsoland;

import com.SpringbootStart;
import com.myIsoland.util.SnowflakeIdWorker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootStart.class)
public class SnowFlowTest {
    @Test
    public void Test(){
        System.out.println("测试1："+ SnowflakeIdWorker.getUUID());
    }
}
