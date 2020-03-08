package com.myIsoland.service.debate;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myIsoland.common.util.CaculateUtils;
import com.myIsoland.constant.ProjectConstant;
import com.myIsoland.enitity.debate.Topic;
import com.myIsoland.mapper.debate.TopicMapper;
import com.myIsoland.model.UserInfo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TopicServiceImpl extends ServiceImpl<TopicMapper,Topic> implements TopicService{
        @Override
    public Topic GetCurtTopic() {
            LambdaQueryWrapper<Topic> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Topic::getFinish,1)
                    .eq(Topic::getIsDel,0)
                    .orderByDesc(Topic::getCreateDat)
                    .select(Topic::getUid,Topic::getTitle,Topic::getContent,Topic::getYear,Topic::getFinish,Topic::getTimes,Topic::getViews,Topic::getBelongName)
                    .last("LIMIT 1");
        return deletePrefix(this.baseMapper.selectOne(wrapper));
    }

    @Override
    public List<UserInfo> GetLastThree(String year, int times) {
        return this.baseMapper.queryThreeAns(year,times);
    }


    @Override
    public List<Topic> GetHotTopic() {
        LambdaQueryWrapper<Topic> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Topic::getFinish,1)
                .eq(Topic::getIsDel,0)
                .orderByDesc(Topic::getAnswers)
                .select(Topic::getUid,Topic::getTitle,Topic::getYear,Topic::getTimes)
                .last("limit 3");
        return deleteTopicsPrefix(this.baseMapper.queryHotTopic());
    }

    @Override
    public Topic GetTopicTetail(String uid) {
        uid = ProjectConstant.TOPICPREFIX+uid;
        return deletePrefix(this.baseMapper.selectById(uid));
    }

    private Topic deletePrefix(Topic data){

        data.setUid(CaculateUtils.translateId(ProjectConstant.TOPICPREFIX,data.getUid()));
        data.setCreateBy(CaculateUtils.translateId(ProjectConstant.USERPREFIX,data.getCreateBy()));
        return data;
    }

    private List<Topic> deleteTopicsPrefix(List<Topic> data){
        for(Topic item :data){
            item.setUid(CaculateUtils.translateId(ProjectConstant.TOPICPREFIX,item.getUid()));
            item.setCreateBy(CaculateUtils.translateId(ProjectConstant.USERPREFIX,item.getCreateBy()));
        }
        return data;
    }
}
