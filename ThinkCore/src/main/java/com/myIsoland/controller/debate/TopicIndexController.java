package com.myIsoland.controller.debate;

import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.common.util.SnowflakeIdWorker;
import com.myIsoland.constant.ProjectConstant;
import com.myIsoland.enitity.debate.Topic;
import com.myIsoland.enums.CodeEnum;
import com.myIsoland.model.ResultSet;
import com.myIsoland.service.debate.TopicService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "论题检索界面")
@RequestMapping("TopicIndex")
public class TopicIndexController {

    @Autowired
    private TopicService topicService;


    /**
     * @param startIndex
     * @param pageSize
     * @Author:THINKPAD
     * @Description:读取列期论题
     * @Return:a
    * @Data:14:06 2020/6/6
     **/
    @GetMapping("/readTopicList")
    public Object ReadTopicList(int startIndex,int pageSize){
        ResultSet<Topic> resultSet = topicService.GetTopicList(startIndex,pageSize);
        return AjaxResult.success(resultSet);
    }


    /**
     *@Author:THINKPAD
     *@Description:创建论题推荐
     * @param content
     * @param content
     *@Return:java.lang.Object
     *@Data:23:32 2020/1/26
     **/
    @PostMapping("/createTopic")
    public Object CreateTopic(String title,String content){
        Topic data = new Topic();
        data.setTitle(title);
        data.setContent(content);
        data.setUid(ProjectConstant.TOPICPREFIX + SnowflakeIdWorker.getUUID());
        data.setFinish(-1);//未审核
        topicService.save(data);
        return AjaxResult.success(CodeEnum.SQL_SUCCESS.getMessage());

    }
}
