package com.myIsoland.controller.community;

import com.myIsoland.common.component.RedisCacheService;
import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.enitity.community.Activity;
import com.myIsoland.service.community.ActivityService;
import com.myIsoland.shiro.util.ShiroUtils;
import com.myIsoland.util.DateUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 活动controller
 * @author xuyong
 * @date: 2019年11月20日 下午4:23:50
 */
@RestController
@Api(value = "活动信息")
@RequestMapping("Activity")
public class ActivityController {
    @Autowired
    private ActivityService activityService;
    @Autowired
    private RedisCacheService redisCacheService;

    /**
     *@Author:THINKPAD
     *@Description:获取热门活动
     * @param date
     * @param  views
     *@Return:java.lang.Object
     *@Data:17:23 2019/11/24
     **/
    @GetMapping("/readHotActivity")
    public Object ReadHotActivity(String date,int views){
        return AjaxResult.success(activityService.GetHotActivity(DateUtils.parseDate(date),views));
    }


    /**
     *@Author:THINKPAD
     *@Description:根据活动id获取活动信息
     * @param activityId
     *@Return:java.lang.Object
     *@Data:20:06 2019/11/24
     **/
    @GetMapping("/readActivityInfo")
    public Object ReadActivityInfo(String activityId){

        redisCacheService.zsetByScore("act_views",activityId,1);
        return AjaxResult.success(activityService.getById(activityId));

    }



    /**
     *@Author:THINKPAD
     *@Description:根据用户id获取关注活动信息
     *@Return:java.lang.Object
     *@Data:20:06 2019/11/24
     **/
    @GetMapping("/readConActivity")
    public Object ReadConcernActivity(String date,int page){
        String userId = ShiroUtils.getUserId();
        List<Activity> data = activityService.GetConcernActvity(userId,DateUtils.parseDate(date),page);
        return AjaxResult.success(data);
    }

    /**
     *@Author:THINKPAD
     *@Description:根据用户id刷新关注活动信息
     *@Return:java.lang.Object
     *@Data:20:06 2019/11/24
     **/
    @GetMapping("/readRefreshConActivity")
    public Object ReadRefreshConActivity(String date,int page){
        String userId = ShiroUtils.getUserId();
        List<Activity> data = activityService.GetRefreshConActvity(userId,DateUtils.parseDate(date),page);
        return AjaxResult.success(data);
    }
}
