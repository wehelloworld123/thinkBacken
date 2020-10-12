package com.myIsoland.service.debate;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.debate.Topic;
import com.myIsoland.model.ResultSet;
import com.myIsoland.model.UserInfo;

import java.util.List;
import java.util.Map;

public interface TopicService extends IService<Topic> {

    Topic GetCurtTopic();

    //获取上期的前三名
    List<Map<String,Object>> GetLastThree(String year , int times);

    List<Topic> GetHotTopic(int startIndex,int pageSize);

    Topic GetTopicTetail(String uid);

    ResultSet<Topic> GetTopicList(int startIndex,int pageSize);
}
