package com.myIsoland.controller.personal;

import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.common.util.DateUtils;
import com.myIsoland.constant.THINKConstant;
import com.myIsoland.enitity.debate.Topic;
import com.myIsoland.enitity.debate.UserTopic;
import com.myIsoland.model.UserInfo;
import com.myIsoland.service.debate.UserTopicService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "个人立论界面")
@RequestMapping("PersonalDabat")
public class PersonalDebatController {
    @Autowired
    private UserTopicService userTopicService;

    /**
     *@Author:THINKPAD
     *@Description:用户获取自己的立论信息
     * @param date
     *@Return:java.lang.Object
     *@Data:22:28 2020/1/27
     **/
    @GetMapping("/readUserDebatInfo")
    public Object ReadUserDebatInfo(String date){
        Map<String,Object> data = new HashMap<>();
        try {
            List<Map<String,Object>> rankAns = userTopicService.GetUserRankAns();
            List<Topic> topics = userTopicService.GetUserAnsTopic(DateUtils.parseDate(date));
            List<UserTopic> userTopics = userTopicService.GetUserRefBook();
            data.put("rank",rankAns);
            data.put("book",userTopics);
            data.put("topic",topics);
            return AjaxResult.success(data);
        }catch (Exception e){
           return AjaxResult.error(THINKConstant.SQL_EXCEPTION_CODE,e.getMessage());
        }
    }

    /**
     *@Author:THINKPAD
     *@Description:获取用户参与的话题
     * @param date
     *@Return:java.lang.Object
     *@Data:23:06 2020/1/27
     **/
    @GetMapping("/readUserTopics")
    public Object ReadUserTopics(String date){
        try {
            return AjaxResult.success(userTopicService.GetUserAnsTopic(DateUtils.parseDate(date)));
        }catch (Exception e){
            return AjaxResult.error(THINKConstant.SQL_EXCEPTION_CODE,e.getMessage());
        }
    }
}
