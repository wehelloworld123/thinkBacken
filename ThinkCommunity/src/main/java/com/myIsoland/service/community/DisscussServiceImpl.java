package com.myIsoland.service.community;

import cn.hutool.core.date.DateTime;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myIsoland.enitity.community.Disscuss;
import com.myIsoland.enitity.community.UserDiscuss;
import com.myIsoland.mapper.community.DisscussMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DisscussServiceImpl extends ServiceImpl<DisscussMapper,Disscuss> implements DisscussService {
    @Autowired
    private DisscussMapper disscussMapper;
    @Override
    public List<Disscuss> GetHotDisscuss() {
        return disscussMapper.queryHotDiscuss();
    }

    @Override
    public List<Disscuss> GetHotDisscuss(int like) {
        return disscussMapper.queryHotDiscuss(like);
    }

    @Override
    public List<Disscuss> GetNewDisscuss() {
        return disscussMapper.queryNewDiscuss();
    }

    @Override
    public List<Disscuss> GetNewDisscuss(DateTime date) {
        return this.baseMapper.queryNewDiscuss(date);
    }

    @Override
    public List<Disscuss> GetConcernDis(String userId) {
        return disscussMapper.queryConcernDis(userId);
    }

    @Override
    public List<Disscuss> GetConcernDis(String userId, DateTime dateTime) {
        return disscussMapper.queryConcernDis(userId,dateTime);
    }


    @Override
    public int SaveDisscuss(Disscuss data) {
        return disscussMapper.insert(data);
    }
}
