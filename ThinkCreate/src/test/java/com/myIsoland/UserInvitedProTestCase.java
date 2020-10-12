package com.myIsoland;

import com.SpringbootStart;
import com.myIsoland.common.util.DateUtils;
import com.myIsoland.common.util.SnowflakeIdWorker;
import com.myIsoland.enitity.product.Literature;
import com.myIsoland.enitity.product.Poetry;
import com.myIsoland.enitity.product.UserCreation;
import com.myIsoland.enitity.product.UserProduct;
import com.myIsoland.enums.CreateEnum;
import com.myIsoland.enums.CreateKind;
import com.myIsoland.enums.ProStatus;
import com.myIsoland.enums.RecomType;
import com.myIsoland.service.product.LiteratureService;
import com.myIsoland.service.product.PaintingService;
import com.myIsoland.service.product.PoetryService;
import com.myIsoland.service.product.UserProductService;
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
public class UserInvitedProTestCase {
    @Autowired
    private LiteratureService literatureService;
    @Autowired
    private UserProductService userProductService;
    @Autowired
    private PoetryService poetryService;
    @Autowired
    private PaintingService paintingService;
    @Test
    public void countRecommend() {
        System.out.println(userProductService.GetUserLiteratures("488294747442511879",3,1, DateUtils.getNowDate(),0,10));
    }
    @Test
    public void InsertRecommend(){
        List<Literature> Llist = literatureService.GetInitLiterByType(CreateKind.HISTORY,99999,99999,20);
        List<Poetry> Plist = poetryService.GetPoetryByType(CreateEnum.BRAND,99999,99999,20);
        List<UserProduct> list = new ArrayList<>();
        for (int i=0;i<Llist.size();i++){
            UserProduct d = new UserProduct();
            d.setTyp(RecomType.LITERATURE.getValue());
            d.setUserId("488294747442511879");
            d.setKind(ProStatus.noFinish.getValue());
            d.setCreationId(Llist.get(i).getUid());
            list.add(d);
        }
        for (int i=0;i<Plist.size();i++){
            UserProduct d = new UserProduct();
            d.setTyp(RecomType.LITERATURE.getValue());
            d.setUserId("488294747442511879");
            d.setKind(ProStatus.noFinish.getValue());
            d.setCreationId(Plist.get(i).getUid());
            list.add(d);
        }
        userProductService.saveBatch(list);
        list.clear();
        for (int i=0;i<Llist.size();i++){
            UserProduct d = new UserProduct();
            d.setTyp(RecomType.LITERATURE.getValue());
            d.setUserId("488294747442511879");
            d.setKind(ProStatus.urgent.getValue());
            d.setCreationId(Llist.get(i).getUid());
            list.add(d);
        }
        for (int i=0;i<Plist.size();i++){
            UserProduct d = new UserProduct();
            d.setTyp(RecomType.LITERATURE.getValue());
            d.setUserId("488294747442511879");
            d.setKind(ProStatus.urgent.getValue());
            d.setCreationId(Plist.get(i).getUid());
            list.add(d);
        }
        userProductService.saveBatch(list);
        list.clear();
        for (int i=0;i<Llist.size();i++){
            UserProduct d = new UserProduct();
            d.setTyp(RecomType.LITERATURE.getValue());
            d.setUserId("488294747442511879");
            d.setKind(ProStatus.finish.getValue());
            d.setCreationId(Llist.get(i).getUid());
            list.add(d);
        }
        for (int i=0;i<Plist.size();i++){
            UserProduct d = new UserProduct();
            d.setTyp(RecomType.LITERATURE.getValue());
            d.setUserId("488294747442511879");
            d.setKind(ProStatus.finish.getValue());
            d.setCreationId(Plist.get(i).getUid());
            list.add(d);
        }
        userProductService.saveBatch(list);
        list.clear();
        for (int i=0;i<Llist.size();i++){
            UserProduct d = new UserProduct();
            d.setTyp(RecomType.LITERATURE.getValue());
            d.setUserId("488294747442511879");
            d.setKind(ProStatus.store.getValue());
            d.setCreationId(Llist.get(i).getUid());
            list.add(d);
        }
        for (int i=0;i<Plist.size();i++){
            UserProduct d = new UserProduct();
            d.setTyp(RecomType.LITERATURE.getValue());
            d.setUserId("488294747442511879");
            d.setKind(ProStatus.store.getValue());
            d.setCreationId(Plist.get(i).getUid());
            list.add(d);
        }
        userProductService.saveBatch(list);
        list.clear();
        System.out.print("----------------------------");
        System.out.println("XXXXXXX555555xxxxxxxxx:"+userProductService);


    }
}
