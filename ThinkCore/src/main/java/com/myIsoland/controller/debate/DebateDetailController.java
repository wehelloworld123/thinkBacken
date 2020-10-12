package com.myIsoland.controller.debate;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.constant.ProjectConstant;
import com.myIsoland.constant.THINKConstant;
import com.myIsoland.enitity.debate.Answer;
import com.myIsoland.enitity.debate.Recommend;
import com.myIsoland.service.debate.AnswerService;
import com.myIsoland.service.debate.RecommendService;
import com.myIsoland.service.debate.TopicService;
import com.myIsoland.shiro.util.ShiroUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "立论详情界面")
@RequestMapping("DebateDetail")
public class DebateDetailController {
    @Autowired
    private AnswerService answerService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private RecommendService recommendService;


    /**
     *@Author:THINKPAD
     *@Description:获取论点详情
     * @param id
     *@Return:java.lang.Object
     *@Data:23:45 2020/1/26
     **/
    @GetMapping("/readTopicDetail")
    public Object ReadTopicDetail(String id){
            return AjaxResult.success(topicService.GetTopicTetail(id));
    }
    /**
     *@Author:THINKPAD
     *@Description:读取论点详情
     * @param id
     *@Return:java.lang.Object
     *@Data:0:43 2020/1/26
     **/
    @GetMapping("/readTopicAns")
    public Object ReadTopicAns(String id,String date,int start,int limit){
            return AjaxResult.success(answerService.GetAnswersByDate(id, DateUtil.parseDate(date),start,limit));

    }

    /**
     *@Author:THINKPAD
     *@Description:读取优解回答
     * @param id
     * @param date
     * @param page
     *@Return:java.lang.Object
     *@Data:18:54 2020/1/26
     **/
    @GetMapping("/readAdvanceAns")
    public Object ReadAdvanceAns(String id,String date,int likes,int recoms,int start,int limit){

        return AjaxResult.success(answerService.GetAdvanceAnsByTopicId(id,DateUtil.parseDate(date),likes,recoms,start,limit));

    }

    /**
     *@Author:THINKPAD
     *@Description:创建立论答案
     * @param content
     * @param summary
     * @param topicId
     *@Return:java.lang.Object
     *@Data:23:23 2020/1/26
     **/
    @PostMapping("/createAnswer")
    public Object CreateAnswer(String content,String conclusion,String topicId){
        Answer data = new Answer();
        data.setContent(content);
        data.setConclusion(conclusion);
        data.setTopicId(ProjectConstant.TOPICPREFIX + topicId);
        answerService.InsertAnswer(data);
        return AjaxResult.success(data);


    }



    /**
     *@Author:THINKPAD
     *@Description:获取推荐内容
     * @param ansId
     * @param date
     *@Return:java.lang.Object
     *@Data:23:42 2020/1/26
     **/
/*    @GetMapping("/readRecommends")
    public Object ReadRecommends(String ansId,String date){
        try{
            return AjaxResult.success(recommendService.GetNextRecom(ansId,DateUtil.parseDate(date)));
        }catch (Exception e){
            return AjaxResult.error(THINKConstant.SQL_EXCEPTION_CODE,e.getMessage());
        }
    }*/
}
