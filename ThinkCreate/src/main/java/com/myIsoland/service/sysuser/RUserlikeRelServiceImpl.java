package com.myIsoland.service.sysuser;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myIsoland.enitity.sysuser.RUserlikeRel;
import com.myIsoland.mapper.sysuser.RUserlikeRelMapper;

public class RUserlikeRelServiceImpl extends ServiceImpl<RUserlikeRelMapper,RUserlikeRel> implements RUserlikeRelService {
    @Override
    public int SaveRUserlike(RUserlikeRel data) {
        return this.baseMapper.insert(data);
    }
}
