package com.myIsoland.service.community;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myIsoland.enitity.community.UserDiscuss;
import com.myIsoland.mapper.community.UserDiscussMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDiscussServiceImpl extends ServiceImpl<UserDiscussMapper,UserDiscuss> implements UserDiscussService{

    @Override
    public int SaveUserDiscuss(UserDiscuss data) {
        return this.baseMapper.insert(data);
    }

    @Override
    public int DelUserDiscuss(Integer discussId, String userId) {
        return this.baseMapper.delUserDicuss(discussId,userId);
    }


}
