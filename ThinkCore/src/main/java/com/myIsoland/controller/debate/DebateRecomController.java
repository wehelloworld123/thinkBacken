package com.myIsoland.controller.debate;


import cn.hutool.core.date.DateUtil;
import com.myIsoland.common.component.RedisCacheService;
import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.constant.ProjectConstant;
import com.myIsoland.constant.RedisConstant;
import com.myIsoland.enitity.debate.Answer;
import com.myIsoland.enitity.debate.Recommend;
import com.myIsoland.enums.CodeEnum;
import com.myIsoland.model.ResultSet;
import com.myIsoland.service.debate.AnswerService;
import com.myIsoland.service.debate.RecommendService;
import com.myIsoland.shiro.util.ShiroUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "立论推荐")
@RequestMapping("DebateRecom")
public class DebateRecomController {

    @Autowired
    private AnswerService answerService;

    @Autowired
    private RedisCacheService redisCacheService;

    @Autowired
    private RecommendService recommendService;

    /**
     *@Author:THINKPAD
     *@Description:获取用户立论详情
     * @param no
     *@Return:java.lang.Object
     *@Data:23:45 2020/1/26
     **/
    @GetMapping("/readAnsDetail")
    public Object ReadAnsDetail(String no,Boolean isLogin){
        no = ProjectConstant.ANSPREFIX +no;
        String userId=null;
        if(isLogin){
            userId = ShiroUtils.getUserId();
        }
        Answer answer = answerService.GetAnswerById(no,userId);
        redisCacheService.zsetByScore("t_deb_ans_views",no,1);
        return AjaxResult.success(answer);
    }

    /**
     *@Author:THINKPAD
     *@Description:更新内容点赞喜欢
     * @param no
     *@Return:java.lang.Object
     *@Data:13:42 2020/2/4
     **/
    @PostMapping("/modifyLikesSts")
    public Object ModifyLikesSts(String no,int type){
        String userId = ShiroUtils.getUserId();
        no = ProjectConstant.ANSPREFIX+no;

        if (type==1){
            redisCacheService.setHashValue(RedisConstant.DRECOM+no,userId,"1");
            System.out.println(redisCacheService.getHashMap(RedisConstant.DRECOM+no));
        }else if(type==0){
            Boolean b = redisCacheService.hashFieldExist(RedisConstant.DRECOM+no,userId);
            if(b){
                if(redisCacheService.delHashField(RedisConstant.DRECOM+no,userId).equals(0)){
                    return AjaxResult.error(CodeEnum.REDIS_EXCEPTION.getCode(), CodeEnum.REDIS_EXCEPTION.getMessage(),null);
                }
            }else {
                answerService.delLikeSts(no,userId);
            }
        }
        return AjaxResult.success(CodeEnum.SQL_SUCCESS.getMessage());
    }
    /**
     *@Author:THINKPAD
     *@Description:获取用户立论推荐信息
     * @param ansId
     * @param date
     * @param start
     * @param limit
     *@Return:java.lang.Object
     *@Data:23:45 2020/1/26
     **/
    @GetMapping("/readUserAnsRecom")
    public Object ReadUserAnsRecom(String ansId,String date,int start,int limit){
        ResultSet<Recommend> resultSet = recommendService.GetUserRecomByDate(ShiroUtils.getUserId(), ansId,DateUtil.parseDate(date),start,limit);
        return AjaxResult.success(resultSet);
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
        data.setAnsId(ProjectConstant.ANSPREFIX+ansId);
        recommendService.SaveRecommend(data);
        return AjaxResult.success(CodeEnum.SQL_SUCCESS.getMessage());

    }
}
