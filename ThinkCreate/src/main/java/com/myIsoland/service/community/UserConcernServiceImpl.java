package com.myIsoland.service.community;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myIsoland.enitity.community.UserConcern;
import com.myIsoland.mapper.community.UserConcernMapper;
import org.springframework.stereotype.Service;

@Service
public class UserConcernServiceImpl extends ServiceImpl<UserConcernMapper,UserConcern> implements UserConcernService {

    @Override
    public int SaveUserDiscuss(UserConcern data) {
        return this.baseMapper.insert(data);
    }

    @Override
    public int DelUserDiscuss(Long discussId, String userId) {
        return this.baseMapper.delUserDicuss(discussId,userId);

    }

    @Override
    public int DelUserActivity(String activityId, String userId) {
        return this.baseMapper.delUserActivity(activityId,userId);
    }


}
