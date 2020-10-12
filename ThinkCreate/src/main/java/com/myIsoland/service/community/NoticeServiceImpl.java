package com.myIsoland.service.community;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myIsoland.common.base.BaseEntity;
import com.myIsoland.enitity.community.Notice;
import com.myIsoland.mapper.community.NoticeMapper;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper,Notice> implements NoticeService {
    @Override
    public Notice GetCurrentNotice(String corpId) {
        LambdaQueryWrapper<Notice> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Notice::getLink,corpId)
                .eq(Notice::getType,3)
                .eq(Notice::getIsDel,0)
                .orderByDesc(Notice::getCreateDat)
                .select(Notice::getId, Notice::getTitle, Notice::getContent, Notice::getCreator, Notice::getDate, BaseEntity::getCreateDat)
                .last("limit 1");
        return this.baseMapper.selectOne(wrapper);
    }
}
