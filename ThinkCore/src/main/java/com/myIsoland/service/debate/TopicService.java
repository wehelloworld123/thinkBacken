package com.myIsoland.service.debate;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.debate.Topic;
import com.myIsoland.model.UserInfo;

import java.util.List;

public interface TopicService extends IService<Topic> {

    Topic GetCurtTopic();

    //获取上期的前三名
    List<UserInfo> GetLastThree(String year ,int times);

    List<Topic> GetHotTopic();

    Topic GetTopicTetail(String uid);

}
