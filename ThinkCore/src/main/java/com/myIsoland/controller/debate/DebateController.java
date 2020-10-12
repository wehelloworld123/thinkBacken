package com.myIsoland.controller.debate;

import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.common.util.StringUtils;
import com.myIsoland.constant.THINKConstant;
import com.myIsoland.enitity.debate.Answer;
import com.myIsoland.enitity.debate.Topic;
import com.myIsoland.model.UserInfo;
import com.myIsoland.service.debate.TopicService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Year;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "立论界面")
@RequestMapping("Debate")
public class DebateController {

    @Autowired
    private TopicService topicService;


    /**
     *@Author:THINKPAD
     *@Description:读取讨论立题
     * @param
     *@Return:java.lang.Object
     *@Data:21:13 2020/1/25
     **/
    @GetMapping("/readDbateInfo")
    public Object ReadDebateInfo(){

        Map data = new HashMap<String, Object>();
        Topic topic = topicService.GetCurtTopic();
        List<Map<String,Object>> userInfos = null;
        if (topic.getPeriod() == 1) {
            int year = Integer.parseInt(topic.getYear()) - 1;
            userInfos = topicService.GetLastThree(year + "", 12);
        }else {
            userInfos = topicService.GetLastThree(topic.getYear(), topic.getPeriod() - 1);
        }
        List<Topic> topics = topicService.GetHotTopic(0,3);
        data.put("topic",topic);
        data.put("userInfo",userInfos);
        data.put("hotTopics",topics);
        return AjaxResult.success(data);

    }




    /**
     *@Author:THINKPAD
     *@Description:读取用户最新理论列表
     * @param
     *@Return:java.lang.Object
     *@Data:21:13 2020/1/25
     **/
    @GetMapping("/readDebatsByDate")
    public Object ReadDebatsByDate(String id,String date,int start,int limit){

            Map data = new HashMap<String, Object>();
            Topic topic = topicService.GetCurtTopic();
            List<Map<String,Object>> userInfos = null;
            if (topic.getPeriod() == 1) {
                int year = Integer.parseInt(topic.getYear()) - 1;
                userInfos = topicService.GetLastThree(year + "", 12);
            }else {
                userInfos = topicService.GetLastThree(topic.getYear(), topic.getPeriod() - 1);
            }
            List<Topic> topics = topicService.GetHotTopic(0,3);
            data.put("topic",topic);
            data.put("userInfo",userInfos);
            data.put("hotTopics",topics);
            return AjaxResult.success(data);

    }
}
