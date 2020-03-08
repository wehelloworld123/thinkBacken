package com.myIsoland;

import com.SpringbootStart;
import com.myIsoland.enitity.product.Literature;
import com.myIsoland.enitity.product.Recommend;
import com.myIsoland.enums.CreateKind;
import com.myIsoland.enums.RecomType;
import com.myIsoland.service.product.RecommendService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootStart.class)
public class RecommendTestCase {
    @Autowired
    private RecommendService recommendService;
    @Test
    public void InsertRecommend(){
        Recommend data =new Recommend();
        int result = recommendService.SaveRecomment(data);
        Assert.assertEquals(1,result);

    }
}
