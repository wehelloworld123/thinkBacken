package com.myIsoland.controller.debate;

import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.enitity.debate.Topic;
import com.myIsoland.model.UserInfo;
import com.myIsoland.service.debate.TopicService;
import io.swagger.annotations.Api;
import net.sf.jsqlparser.statement.select.Top;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "专题界面")
@RequestMapping("Subject")
public class SubjectController {

    @Autowired
    private TopicService topicService;

    /**
     * 获取专栏页面初始化数据
     * @return
     */
    @GetMapping("/readInitPageData")
    public Object  ReadInitPageData() {

        Map<String,Object> data = new HashMap<>();

        /**********
         * Step 1
         * 获取本期论点
         **********/
        Topic topic = topicService.GetCurtTopic();
       String year;
       int times =0;
       if(topic.getPeriod()==1){
           year = (Integer.parseInt(topic.getYear())-1)+"";
           times = 12;
       }else {
           year = topic.getYear();
           times = topic.getPeriod();
       }
        /**********
         * Step 2
         * 获取上期榜单
         **********/
       List<Map<String,Object>> list =  topicService.GetLastThree(year,times);

       List<Topic> topicList = topicService.GetHotTopic(0,3);


       data.put("entity",topic);
       data.put("list",list);
       data.put("topicList",topicList);
        return AjaxResult.success(data);
    }
}
