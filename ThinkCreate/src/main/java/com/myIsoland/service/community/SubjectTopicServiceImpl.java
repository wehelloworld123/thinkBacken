package com.myIsoland.service.community;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.myIsoland.common.base.BaseEntity;
import com.myIsoland.enitity.community.SubjectTopic;
import com.myIsoland.mapper.community.SubjectTopicMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectTopicServiceImpl extends ServiceImpl<SubjectTopicMapper,SubjectTopic> implements SubjectTopicService {
    @Override
    public List<SubjectTopic> GetSubjectTopicByHot(int startIndex, int pageSize) {
        PageHelper.offsetPage(startIndex,pageSize,true);
        QueryWrapper<SubjectTopic> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .eq(BaseEntity::getIsDel,0)
                .orderByDesc(SubjectTopic::getCreator)
                .select(SubjectTopic::getTopicId,SubjectTopic::getTopic,SubjectTopic::getCreator,SubjectTopic::getLabel1,
                        SubjectTopic::getLabel2,SubjectTopic::getLabel3,SubjectTopic::getDescription,SubjectTopic::getDn,SubjectTopic::getLogo);
        List<SubjectTopic> list = this.baseMapper.selectList(wrapper);
        return list;
    }
}
