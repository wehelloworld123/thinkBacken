package com.myIsoland;

import com.SpringbootStart;
import com.myIsoland.common.util.DateUtils;
import com.myIsoland.common.util.SnowflakeIdWorker;
import com.myIsoland.constant.ProjectConstant;
import com.myIsoland.enitity.product.PoemContent;
import com.myIsoland.enitity.product.PoemSet;
import com.myIsoland.enitity.product.Poetry;
import com.myIsoland.model.ResultSet;
import com.myIsoland.service.product.PoemContentService;
import com.myIsoland.service.product.PoemSetService;
import com.myIsoland.service.product.PoetryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest

public class PoetryTestCase {

    @Autowired
    private PoetryService poetryService;
    @Autowired
    private PoemContentService poemContentService;
    @Autowired
    private PoemSetService poemSetService;
    @Test
    public void init(){
        List<PoemSet> list;
        list = poemSetService.GetPoemSetsByPoetryId("TPOETRY404964942081560580",0,50,false);
        for(PoemSet poemSet : list) {
            List<PoemContent> datas = new ArrayList<>();
            for(int i=0;i<50;i++){
                PoemContent data = new PoemContent();
                data.setCharpId(poemSet.getId());
                data.setNo(ProjectConstant.CPOETRYPREFIX+SnowflakeIdWorker.getUUID());
                data.setBrand("满江红"+i);
                data.setTitle("江南号"+i);
                data.setFavorer(";");
                data.setContent("\t\t\t\t从明天起，做一个幸福的人\n" +
                        "\t\t\t\t喂马、劈柴，周游世界\n" +
                        "\t\t\t\t从明天起，关心粮食和蔬菜\n" +
                        "\t\t\t\t我有一所房子，面朝大海，春暖花开 我有一所房子，面朝大海，春暖花开\n" +
                        "\t\t\t\t从明天起，和每一个亲人通信\n" +
                        "\t\t\t\t告诉他们我的幸福\n" +
                        "\t\t\t\t那幸福的闪电告诉我的\n" +
                        "\t\t\t\t我将告诉每一个人\n" +
                        "\t\t\t\t给每一条河每一座山取一个温暖的名字\n" +
                        "\t\t\t\t陌生人，我也为你祝福\n" +
                        "\t\t\t\t愿你有一个灿烂的前程\n" +
                        "\t\t\t\t愿你有情人终成眷属\n" +
                        "\t\t\t\t愿你在尘世获得幸福\n" +
                        "\t\t\t\t我只愿面朝大海，春暖花开");
                data.setLikes(i*5);
                data.setRecomNo(i*2);

                datas.add(data);
            }
            poemContentService.saveBatch(datas);
        }
    }
    @Test
    public void check(){
        ResultSet<PoemContent> result = poemContentService.GetContentsOrderByDate(
                new Long(2),DateUtils.getNowDate(),2,10,new ArrayList<>());
        System.out.println(result.getList().size());
        System.out.println(result.getCount());
    }

    @Test
    public void  test() {
        List<Poetry> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Poetry data = new Poetry();
            data.setUid(ProjectConstant.POETRYPREFIX+ SnowflakeIdWorker.getUUID());
            data.setCopyright(i);
            data.setCharpter(10+i);
            data.setSection(100+i);
            data.setDeadline(DateUtils.getNowDate());
            data.setPartner(i+1);

            data.setViews(new Long(i*40));
            if(i<50) {
                data.setKind("001");
            }else{
                data.setKind("002");
            }
            data.setDescription("世界第"+i+"大诗集");
            data.setFinCharpt(i);
            data.setFinSection(5+i);
            data.setFinish(0);
            data.setName("繁花似锦"+i);
            data.setSeter("诗歌集"+i);
            data.setForm("重创");
            data.setPurpose("改变世界");
            data.setCover("https://ossweb-img.qq.com/images/lol/web201310/skin/big84000.jpg");
            data.setReward("3000-5000");
            data.setIntroduce("落魄作家为了写作素材，寻访到了一个叫做吴邪寻访到了一个叫做吴邪寻访到了一个叫做吴邪寻访到了一个叫做吴邪寻访到了一个叫做吴邪寻访到了一个叫做吴邪寻访到了一个叫做吴邪寻访到了一个叫做吴邪（鹿晗 饰）的古董铺子老板，而吴邪正准备离开这个城");
            data.setPublisher("平台自创");
            list.add(data);
        }
        poetryService.saveBatch(list);
    }

}
