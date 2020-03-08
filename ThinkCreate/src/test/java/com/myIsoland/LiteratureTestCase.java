package com.myIsoland;

import com.SpringbootStart;

import com.myIsoland.enitity.product.Literature;
import com.myIsoland.enums.CreateKind;
import com.myIsoland.enums.RecomType;
import com.myIsoland.service.product.LiteratureService;
import com.myIsoland.service.product.UserCreationService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootStart.class)
public class LiteratureTestCase {
    @Autowired
    private LiteratureService literatureService;
    @Autowired
    private UserCreationService userCreationService;
    @Test
    public void InsertRecommend(){
//        Literature data = new Literature();
//        data.setKind(4);
//        data.setCover("http://www");
//        for (int i=0;i<50;i++){
//
//            data.setUid("uid"+i);
//            data.setName("001"+i);
//            data.setType("uuu"+i);
//            data.setLabel("label");
//            literatureService.SaveLiterature(data);
//        }

        System.out.print("----------------------------");
        System.out.println("XXXXXXX555555xxxxxxxxx:"+userCreationService.GetCreatPartInfo("TLITER1", RecomType.LITERATURE));
        System.out.println("XXXXXXX00000xxxxxxxxx:"+literatureService.GetLiteratureById("TLITER1"));
        System.out.println("XXXXXXX11111xxxxxxxxx:"+literatureService.GetLiteratureByType(CreateKind.GAME,0,1));
       System.out.println("XXXXXXX222222xxxxxxxxx:"+literatureService.GetLiteratureByType(CreateKind.GAME,1,0));

    }
}
