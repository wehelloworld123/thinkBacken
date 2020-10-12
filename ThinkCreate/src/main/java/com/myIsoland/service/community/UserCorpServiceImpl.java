package com.myIsoland.service.community;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myIsoland.enitity.community.UserCorportion;
import com.myIsoland.mapper.community.UserCorpMapper;
import org.springframework.stereotype.Service;

@Service
public class UserCorpServiceImpl extends ServiceImpl<UserCorpMapper,UserCorportion> implements UserCorpService {
    @Override
    public int SaveUserCorp(UserCorportion data) {
        return this.baseMapper.insert(data);
    }

    @Override
    public int UpdateState(String userId, String corpId) {
        UserCorportion data = new UserCorportion();
        data.setStatus(1);
        UpdateWrapper<UserCorportion> userCorportionUpdateWrapper=new UpdateWrapper<>();
        userCorportionUpdateWrapper.eq("uid",userId).eq("cid",corpId);
        return this.baseMapper.update(data,userCorportionUpdateWrapper);
    }

    @Override
    public int DelUser(String userId, String corpId) {
        UserCorportion data = new UserCorportion();
        data.setStatus(0);
        UpdateWrapper<UserCorportion> userCorportionUpdateWrapper=new UpdateWrapper<>();
        userCorportionUpdateWrapper.eq("uid",userId).eq("cid",corpId);
        return this.baseMapper.update(data,userCorportionUpdateWrapper);
    }
}
