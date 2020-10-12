package com.myIsoland;

import com.SpringbootStart;

import com.myIsoland.common.util.DateUtils;
import com.myIsoland.enitity.product.Literature;
import com.myIsoland.enums.CreateEnum;
import com.myIsoland.enums.CreateKind;
import com.myIsoland.enums.RecomType;
import com.myIsoland.service.product.LiterContentService;
import com.myIsoland.service.product.LiteratureService;
import com.myIsoland.service.product.PoetryService;
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
    private LiterContentService literContentService;
    @Autowired
    private UserCreationService userCreationService;
    @Autowired
    private PoetryService poetryService;
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
        System.out.println("XXXXXXX00000xxxxxxxxx:"+poetryService.GetPoetryByDate(CreateEnum.BRAND, DateUtils.getNowDate(),0,12).size());
        System.out.println("XXXXXXX11111xxxxxxxxx:"+literatureService.GetLiteratureByType(CreateKind.GAME,0,1,10));
       System.out.println("XXXXXXX222222xxxxxxxxx:"+literatureService.GetLiteratureByType(CreateKind.GAME,1,0,10));

    }
    @Test
    public void selectUserContent(){
        System.out.println("XXXXXXX11111xxxxxxxxx:"+literContentService.GetUserLiterContentByDate("488294747442511879",DateUtils.parseDate("2020-05-03 21:58:05"),0,15));
    }
}
