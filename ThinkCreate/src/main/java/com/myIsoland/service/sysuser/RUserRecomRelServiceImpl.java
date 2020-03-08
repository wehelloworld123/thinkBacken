package com.myIsoland.service.sysuser;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myIsoland.enitity.sysuser.RUserRecomRel;
import com.myIsoland.mapper.sysuser.RUserRecomRelMapper;

public class RUserRecomRelServiceImpl extends ServiceImpl<RUserRecomRelMapper,RUserRecomRel> implements RUserRecomRelService {
    @Override
    public int SaveRUserRecom(RUserRecomRel data) {
        return this.baseMapper.insert(data);
    }
}
