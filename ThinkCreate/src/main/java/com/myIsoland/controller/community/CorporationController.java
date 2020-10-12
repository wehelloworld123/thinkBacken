package com.myIsoland.controller.community;

import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.common.util.DateUtils;
import com.myIsoland.constant.THINKConstant;
import com.myIsoland.enitity.community.Activity;
import com.myIsoland.enitity.community.Corporation;
import com.myIsoland.enitity.community.UserCorportion;
import com.myIsoland.enums.CodeEnum;
import com.myIsoland.enums.UserCorpStsType;
import com.myIsoland.service.community.ActivityService;
import com.myIsoland.service.community.CorporationService;
import com.myIsoland.service.community.NoticeService;
import com.myIsoland.service.community.UserCorpService;
import com.myIsoland.shiro.util.ShiroUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 社团controller
 * @author xuyong
 * @date: 2019年11月20日 下午4:23:50
 */
@RestController
@Api(value = "社团信息")
@RequestMapping("Corporation")
public class CorporationController {

    @Autowired
    private CorporationService corporationService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private UserCorpService userCorpService;

    @Autowired
    private NoticeService noticeService;

    public CorporationController(){

    }

    /**
     *@Author:THINKPAD
     *@Description:获取所有社团信息
     * @param
     *@Return:java.lang.Object
     *@Data:18:13 2019/11/23
     **/
    @RequestMapping("/readCorps")
    public Object ReadCorps(){
        return AjaxResult.success(corporationService.GetCorporation());

    }

    /**
     *@Author:THINKPAD
     *@Description:根据id获取社团信息
     * @param
     *@Return:java.lang.Object
     *@Data:18:13 2019/11/23
     **/
    @RequestMapping("/readCorpInfo")
    public Object ReadCorpInfo(String uid,String date){
        Map<String,Object> data = new HashMap<>();
        String userId = ShiroUtils.getUserId();
        Date datetime = DateUtils.parseDate(date);
        Corporation corporation = corporationService.GetCorpInfo(uid,userId);
        List<Activity> activities = activityService.GetActivityByCorpId(uid, datetime,0);
        data.put("corporation",corporation);
        data.put("activities",activities);
        return AjaxResult.success(data);
    }


    /**
     *@Author:THINKPAD
     *@Description:根据活的id获取社团活动详信息
     * @param
     *@Return:java.lang.Object
     *@Data:18:13 2019/11/23
     **/
    @RequestMapping("/readCorpActive")
    public Object ReadCorpActive(String activityId){
            return AjaxResult.success(activityService.getById(activityId));
    }
    /**
     *@Author:THINKPAD
     *@Description:增加点赞
     * @param id
     *@Return:java.lang.Object
     *@Data:19:51 2019/11/23
     **/
    @RequestMapping("/updateLikes")
    public Object UpdateLikes(String id){
        int i = corporationService.SaveLikeCorp(ShiroUtils.getUserId(),id);
        return AjaxResult.success(CodeEnum.SQL_SUCCESS.getMessage());

    }

    /**
     *@Author:THINKPAD
     *@Description:申请加入社团
     * @param corpId
     *@Return:java.lang.Object
     *@Data:23:40 2019/11/23
     **/
    @RequestMapping("/createCorpUser")
    public Object CreateCorpUser(String corpId){
        String userId = ShiroUtils.getUserId();
        UserCorportion data = new UserCorportion(userId,corpId, UserCorpStsType.UNAUDITED);
        data.setId(corpId+"-"+userId);
        userCorpService.saveOrUpdate(data);
        return AjaxResult.success(THINKConstant.SQL_SUCCESS_MSG);

    }

    /**
     *@Author:THINKPAD
     *@Description:获取社团公共公告
     * @param corpId
     *@Return:java.lang.Object
     *@Data:23:35 2020/2/8
     **/
    @GetMapping("/readCorpNotice")
    public Object ReadCorpNotice(String corpId){
        return AjaxResult.success(noticeService.GetCurrentNotice(corpId));
    }

}
