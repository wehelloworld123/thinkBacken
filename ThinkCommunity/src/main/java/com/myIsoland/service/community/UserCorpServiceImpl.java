package com.myIsoland.service.community;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myIsoland.enitity.community.UserCorportion;
import com.myIsoland.mapper.community.UserCorpMapper;

public class UserCorpServiceImpl extends ServiceImpl<UserCorpMapper,UserCorportion> implements UserCorpService {
    @Override
    public int SaveUserCorp(UserCorportion data) {
        return this.baseMapper.insert(data);
    }

    @Override
    public int UpdateState(String userId, String corpId) {
        UserCorportion data = new UserCorportion();
        data.setState(1);
        UpdateWrapper<UserCorportion> userCorportionUpdateWrapper=new UpdateWrapper<>();
        userCorportionUpdateWrapper.eq("u_uid",userId).eq("c_uid",corpId);
        return this.baseMapper.update(data,userCorportionUpdateWrapper);
    }

    @Override
    public int DelUser(String userId, String corpId) {
        UserCorportion data = new UserCorportion();
        data.setState(0);
        UpdateWrapper<UserCorportion> userCorportionUpdateWrapper=new UpdateWrapper<>();
        userCorportionUpdateWrapper.eq("u_uid",userId).eq("c_uid",corpId);
        return this.baseMapper.update(data,userCorportionUpdateWrapper);
    }
}
