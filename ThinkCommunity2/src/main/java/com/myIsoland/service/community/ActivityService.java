package com.myIsoland.service.community;


import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.community.Activity;

import java.util.Date;
import java.util.List;

public interface ActivityService extends IService<Activity> {
    /**
     *@Author:THINKPAD
     *@Description:根据社团id获取活动信息
     * @param corpId
     * @param number
     *@Return:java.util.List<com.myIsoland.enitity.community.Activity>
     *@Data:20:47 2019/11/23
     **/
    List<Activity> GetActivityByCorpId(String corpId,Date date,int page);


    /**
     *@Author:THINKPAD
     *@Description:获取热点活动
     *@Return:java.util.List<com.myIsoland.enitity.community.Activity>
     *@Data:16:42 2019/11/24
     **/
    List<Activity> GetHotActivity(Date update,int views);
    /**


    /**
     *@Author:THINKPAD
     *@Description:获取最新活动
     *@Return:java.util.List<com.myIsoland.enitity.community.Activity>
     *@Data:16:42 2019/11/24
     **/
    List<Activity> GetNewActivity(int number);

    /**
     *@Author:THINKPAD
     *@Description:根据时间获取最新活动
     *@Return:java.util.List<com.myIsoland.enitity.community.Activity>
     *@Data:16:42 2019/11/24
     **/
    List<Activity> GetNewActivityByDate(DateTime date, int number);

    /**
     *@Author:THINKPAD
     *@Description:根据用户id获取关注活动
     *@Return:java.util.List<com.myIsoland.enitity.community.Activity>
     *@Data:16:42 2019/11/24
     **/
    List<Activity> GetConcernActvity(String userId,Date date,int page);


    /**
     *@Author:THINKPAD
     *@Description:根据用户id刷新热门活动
     *@Return:java.util.List<com.myIsoland.enitity.community.Activity>
     *@Data:16:42 2019/11/24
     **/
    List<Activity> GetRefreshHotActvity(Date update,int views);

    /**
     *@Author:THINKPAD
     *@Description:根据用户id刷新关注活动
     *@Return:java.util.List<com.myIsoland.enitity.community.Activity>
     *@Data:16:42 2019/11/24
     **/
    List<Activity> GetRefreshConActvity(String userId,Date date,int page);

}
