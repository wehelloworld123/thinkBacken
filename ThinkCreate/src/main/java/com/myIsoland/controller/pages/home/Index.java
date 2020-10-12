package com.myIsoland.controller.pages.home;

import cn.hutool.core.date.DateUtil;
import com.myIsoland.common.component.RedisCacheService;
import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.enitity.product.*;
import com.myIsoland.service.community.SubjectTopicService;
import com.myIsoland.service.product.*;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@Api(value = "首页")
@RequestMapping("Pages/Home")
public class Index {

    @Autowired
    private PaintContentService paintContentService;

    @Autowired
    private LiteratureService literatureService;

    @Autowired
    private PoemContentService poemContentService;

    @Autowired
    private LiterContentService literContentService;

    @Autowired
    private PoetryService poetryService;

    @Autowired
    private PaintingService paintingService;

    @Autowired
    private SubjectTopicService subjectTopicService;

    @Autowired
    private RedisCacheService redisCacheService;

    @GetMapping("/readInitPageData")
    public Object  ReadInitPageData(boolean isLogin,String keyword,String date){
        String[] keywords = keyword.split(";");

        Map<String,Object> result = new HashMap<>();


        /***********************
         * Step 1
         * 获取系统时间返回
         **********************/
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date endDate = new Date();//获取当前时间

        Calendar c= Calendar.getInstance();

        c.setTime(endDate);
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);

        c.add(Calendar.MONTH,-1);
        Date time = c.getTime();
        String startDate = format.format(time);//获取一个月前的时间

        /***********************
         * Step 2
         * 获取系统推荐的文学，诗歌，绘画作品
         **********************/

        //that.dataList.topicList = res.data.msg.topicList;

        List<PaintContent> paintContentList = paintContentService.GetSysRecomPaintContent(DateUtil.parseDate(startDate),endDate,0,6);//推荐绘画作品

        result.put("paintContentList",paintContentList);

        List<LiterContent> literContentList =  literContentService.GetSysRecomLiterContent(DateUtil.parseDate(startDate),endDate,0,2);//推荐文章

        result.put("literContentList",literContentList);

        //List<PoemContent> poemList =  poemContentService.GetSysRecomPoemContent(DateUtil.parseDate(startDate),endDate,0,3);//推荐诗歌

        List<Literature> literatureList = literatureService.GetSysRecomIpPro(endDate,0,3);//推荐文学作品

        result.put("literatureList",literatureList);

        List<Painting> albumList = paintingService.GetSysRecomPainting(DateUtil.parseDate(startDate),endDate,1,0,4);//推荐绘画集

        result.put("albumList",albumList);

        Literature literature = literatureService.GetSysRecomOnThinPro(DateUtil.parseDate(startDate));//推荐构思文学

        result.put("literature",literature);

        List<Poetry> poetryList = poetryService.GetRecomPoetry(0,3);//推荐诗歌集

        result.put("poetryList",poetryList);



        return AjaxResult.success(literature);

    }
}
