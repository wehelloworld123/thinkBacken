package com.myIsoland.service.debate;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.debate.Topic;
import com.myIsoland.enitity.debate.UserTopic;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserTopicService extends IService<UserTopic> {
    /**
     *@Author:THINKPAD
     *@Description:获取用户优秀回答数
     * @param
     *@Return:java.util.Map<java.lang.String,java.lang.Object>
     *@Data:20:08 2020/1/27
     **/
    List<Map<String,Object>> GetUserRankAns();

    /**
     *@Author:THINKPAD
     *@Description:获取用户参与话题
     * @param date
     *@Return:java.util.List<com.myIsoland.enitity.debate.Topic>
     *@Data:20:09 2020/1/27
     **/
    List<Topic> GetUserAnsTopic(Date date, int startIndex, int pageSize);

    /**
     *@Author:THINKPAD
     *@Description:获取用户引用的书籍
     * @param
     *@Return:java.util.List<com.myIsoland.enitity.debate.UserTopic>
     *@Data:20:10 2020/1/27
     **/
    List<UserTopic> GetUserRefBook(int startIndex, int pageSize);
}
