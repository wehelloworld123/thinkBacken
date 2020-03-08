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
        try {
            Map data = new HashMap<String, Object>();
            Topic topic = topicService.GetCurtTopic();
            List<UserInfo> userInfos = null;
            if (topic.getTimes() == 1) {
                int year = Integer.parseInt(topic.getYear()) - 1;
                userInfos = topicService.GetLastThree(year + "", 12);
            }else {
                userInfos = topicService.GetLastThree(topic.getYear(), topic.getTimes() - 1);
            }
            List<Topic> topics = topicService.GetHotTopic();
            data.put("topic",topic);
            data.put("userInfo",userInfos);
            data.put("hotTopics",topics);
            return AjaxResult.success(data);
        }catch (Exception e){
            return AjaxResult.error(THINKConstant.SQL_EXCEPTION_CODE,e.getMessage());
        }
    }
}
