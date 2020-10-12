package com.myIsoland.service.community;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.community.SubjectTopic;

import java.util.List;

public interface SubjectTopicService extends IService<SubjectTopic> {

    /**
     * 根据热度返回专题列表
     * @param startIndex
     * @param pageSize
     * @return
     */
    List<SubjectTopic> GetSubjectTopicByHot(int startIndex,int pageSize);
}
