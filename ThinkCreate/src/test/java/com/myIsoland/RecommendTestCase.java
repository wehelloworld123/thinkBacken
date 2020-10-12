package com.myIsoland;

import com.SpringbootStart;
import com.myIsoland.common.util.DateUtils;
import com.myIsoland.enitity.product.Comment;
import com.myIsoland.enitity.product.Literature;
import com.myIsoland.enitity.product.PoemContent;
import com.myIsoland.enitity.product.Recommend;
import com.myIsoland.enums.CreateKind;
import com.myIsoland.enums.RecomType;
import com.myIsoland.model.ResultSet;
import com.myIsoland.service.product.CommentService;
import com.myIsoland.service.product.PoemContentService;
import com.myIsoland.service.product.RecommendService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootStart.class)
public class RecommendTestCase {
    @Autowired
    private RecommendService recommendService;
    @Autowired
    private PoemContentService poemContentService;
    @Autowired
    private CommentService commentService;
    @Test
    public void InsertRecommend(){
        Recommend data =new Recommend();
        int result = recommendService.SaveRecomment(data);
        Assert.assertEquals(1,result);

    }
    @Test
    public void InsertReply(){
        List<Recommend> list = recommendService.GetRecommentByContentId("CPOETRY406285493920206874",0);

        for(Recommend item : list) {
            List<Comment> recommends = new ArrayList<>();
            for(int i =0;i<50;i++){
                Comment data = new Comment();
                data.setRootId(item.getId());
                data.setContent("你的回答太好了"+i);
                data.setCreator("半生缘"+i);
                data.setCreatorAvat("\"https://ossweb-img.qq.com/images/lol/web201310/skin/big143004.jpg\"");
                data.setCreatorSex(i>25?1:0);
                data.setLikes(i);
                data.setFavorer(";");

                recommends.add(data);
            }

            commentService.saveBatch(recommends);

        }

    }
    @Test
    public void InsertPoetryRecommend(){
        ResultSet<PoemContent> resultSet = poemContentService.GetContentsOrderByDate(new Long(2), DateUtils.getNowDate(),0,15,new ArrayList<>());

        for(PoemContent item : resultSet.getList()) {
            List<Recommend> recommends = new ArrayList<>();
            for(int i =0;i<50;i++){
                Recommend data = new Recommend();
                data.setContentId(item.getNo());
                data.setCreator("半生缘"+i);
                data.setCreateAvat("https://ossweb-img.qq.com/images/lol/web201310/skin/big143004.jpg");
                data.setContent("这是一个十分美丽而且万梅的是你弄好就不管不顾和今年年会计划变更和"+i);
                data.setKind(RecomType.POEMTY.getValue());
                data.setLikes(i*10);
                data.setFavorer(";");
                recommends.add(data);
            }

            recommendService.saveBatch(recommends);

        }

    }
}
