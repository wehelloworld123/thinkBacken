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
        try{
            return AjaxResult.success(topicService.GetTopicTetail(id));
        }catch (Exception e){
            return AjaxResult.error(THINKConstant.SQL_EXCEPTION_CODE,e.getMessage());
        }
    }
    /**
     *@Author:THINKPAD
     *@Description:读取论点详情
     * @param id
     *@Return:java.lang.Object
     *@Data:0:43 2020/1/26
     **/
    @GetMapping("/readTopicAns")
    public Object ReadTopicAns(String id,String date,int page){
        try{
            topicService.GetTopicTetail(id);
            return AjaxResult.success(answerService.GetNewAnsById(id, DateUtil.parseDate(date),page));
        }catch (Exception e){
            return AjaxResult.error(THINKConstant.SQL_EXCEPTION_CODE,e.getMessage());
        }
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
    public Object ReadAdvanceAns(String id,String date,int page){
        try{
            return AjaxResult.success(answerService.GetAdvanceAnsById(id,DateUtil.parseDate(date),page));
        }catch (Exception e){
            return AjaxResult.error(THINKConstant.SQL_EXCEPTION_CODE,e.getMessage());
        }
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
    public Object CreateAnswer(String content,String summary,String topicId){
        Answer data = new Answer();
        data.setContent(content);
        data.setSummary(summary);
        data.setTopicId(ProjectConstant.TOPICPREFIX + topicId);
        try {
            int i = answerService.InsertAnswer(data);
            if(THINKConstant.SQL_SUCCESS.equals(i)){
                return AjaxResult.success(THINKConstant.SQL_SUCCESS_MSG);
            }else{
                return AjaxResult.error(THINKConstant.SQL_EXCEPTION_CODE,THINKConstant.SQL_FAIL_MSG);
            }
        }catch (Exception e){
            return AjaxResult.error(THINKConstant.SQL_EXCEPTION_CODE,e.getMessage());
        }
    }


    /**
     *@Author:THINKPAD
     *@Description:创建评论
     * @param content
     * @param ansId
     *@Return:java.lang.Object
     *@Data:23:32 2020/1/26
     **/
    @PostMapping("/createRecommend")
    public Object CreateRecommend(String content,String ansId){
        Recommend data = new Recommend();
        data.setContent(content);
        data.setParentId(ProjectConstant.ANSPREFIX+ansId);
        try {
            int i = recommendService.SaveRecommend(data);
            if(THINKConstant.SQL_SUCCESS.equals(i)){
                return AjaxResult.success(THINKConstant.SQL_SUCCESS_MSG);
            }else{
                return AjaxResult.error(THINKConstant.SQL_EXCEPTION_CODE,THINKConstant.SQL_FAIL_MSG);
            }
        }catch (Exception e){
            return AjaxResult.error(THINKConstant.SQL_EXCEPTION_CODE,e.getMessage());
        }
    }
    /**
     *@Author:THINKPAD
     *@Description:获取推荐内容
     * @param ansId
     * @param date
     *@Return:java.lang.Object
     *@Data:23:42 2020/1/26
     **/
    @GetMapping("/readRecommends")
    public Object ReadRecommends(String ansId,String date){
        try{
            return AjaxResult.success(recommendService.GetNextRecom(ansId,DateUtil.parseDate(date)));
        }catch (Exception e){
            return AjaxResult.error(THINKConstant.SQL_EXCEPTION_CODE,e.getMessage());
        }
    }
}
