package com.myIsoland.service.debate;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.myIsoland.common.base.BaseEntity;
import com.myIsoland.common.base.PageInfo;
import com.myIsoland.common.util.CaculateUtils;
import com.myIsoland.constant.ProjectConstant;
import com.myIsoland.enitity.debate.Topic;
import com.myIsoland.mapper.debate.TopicMapper;
import com.myIsoland.model.ResultSet;
import com.myIsoland.model.UserInfo;
import net.sf.jsqlparser.statement.select.Top;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TopicServiceImpl extends ServiceImpl<TopicMapper,Topic> implements TopicService{
        @Override
    public Topic GetCurtTopic() {
            LambdaQueryWrapper<Topic> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Topic::getFinish,0)
                    .eq(Topic::getIsDel,0)
                    .orderByDesc(Topic::getCreateDat)
                    .select(Topic::getUid,Topic::getTitle,Topic::getThinking,Topic::getFlexibility,Topic::getLogicality,Topic::getTotalDiff,Topic::getContent,Topic::getYear,Topic::getFinish,Topic::getPeriod,Topic::getViews,Topic::getBelongName)
                    .orderByDesc(BaseEntity::getCreateDat)
                    .last("LIMIT 1");
        return deletePrefix(this.baseMapper.selectOne(wrapper));
    }

    @Override
    public List<Map<String,Object>> GetLastThree(String year, int times) {
        return this.baseMapper.queryThreeAns(year,times);
    }


    @Override
    public List<Topic> GetHotTopic(int startIndex,int pageSize) {
        LambdaQueryWrapper<Topic> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Topic::getFinish,1)
                .eq(Topic::getIsDel,0)
                .orderByDesc(Topic::getAnswers)
                .select(Topic::getUid,Topic::getTitle,Topic::getYear,Topic::getPeriod)
                .last("limit "+startIndex+","+pageSize);
        return deleteTopicsPrefix(this.baseMapper.queryHotTopic());
    }

    @Override
    public Topic GetTopicTetail(String uid) {
        uid = ProjectConstant.TOPICPREFIX+uid;
        return deletePrefix(this.baseMapper.selectById(uid));
    }

    @Override
    public ResultSet<Topic> GetTopicList(int startIndex, int pageSize) {
        PageHelper.offsetPage(startIndex,pageSize);
        QueryWrapper<Topic> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .eq(Topic::getFinish,-1)
                .groupBy(Topic::getYear)
                .orderByDesc(Topic::getYear,Topic::getPeriod)
                .select(Topic::getUid,Topic::getTitle,Topic::getYear,Topic::getPeriod,Topic::getThinking,Topic::getLogicality,Topic::getFlexibility,Topic::getTotalDiff);
        List<Topic> list = this.baseMapper.selectList(wrapper);
        deleteTopicsPrefix(list);
        ResultSet<Topic> resultSet = new ResultSet<>();
        resultSet.setList(list);

        return resultSet;
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
