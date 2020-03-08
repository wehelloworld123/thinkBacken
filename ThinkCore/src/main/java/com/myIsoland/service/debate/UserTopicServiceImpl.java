package com.myIsoland.service.debate;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myIsoland.common.util.CaculateUtils;
import com.myIsoland.constant.ProjectConstant;
import com.myIsoland.enitity.debate.Topic;
import com.myIsoland.enitity.debate.UserTopic;
import com.myIsoland.mapper.debate.UserTopicMapper;
import com.myIsoland.shiro.util.ShiroUtils;
import org.springframework.stereotype.Service;

import java.lang.invoke.WrongMethodTypeException;
import java.util.Date;
import java.util.List;
import java.util.Map;
@Service
public class UserTopicServiceImpl extends ServiceImpl<UserTopicMapper,UserTopic> implements UserTopicService {
    @Override
    public List<Map<String, Object>> GetUserRankAns() {
        return this.baseMapper.queryUserRankAns(ShiroUtils.getUser().getId());
    }

    @Override
    public List<Topic> GetUserAnsTopic(Date date) {
        return deleteTopicsPrefix(this.baseMapper.GetUserTopics(ShiroUtils.getUserId(),date));
    }

    @Override
    public List<UserTopic> GetUserRefBook() {
        LambdaQueryWrapper<UserTopic> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserTopic::getCreateBy,ShiroUtils.getUserId())
                .eq(UserTopic::getIsDel,0)
                .eq(UserTopic::getStatus,1)
                .orderByDesc(UserTopic::getCreateBy)
                .last("limit 5")
                .select(UserTopic::getReferBook,UserTopic::getReferType);
        return this.baseMapper.selectList(wrapper);
    }


    private List<Topic> deleteTopicsPrefix(List<Topic> data){
        for(Topic item :data){
            item.setUid(CaculateUtils.translateId(ProjectConstant.TOPICPREFIX,item.getUid()));
            item.setCreateBy(CaculateUtils.translateId(ProjectConstant.USERPREFIX,item.getCreateBy()));
        }
        return data;
    }
}
