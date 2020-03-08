package com.myIsoland.service.community;


import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.community.UserConcern;

public interface UserConcernService extends IService<UserConcern> {
    int SaveUserDiscuss(UserConcern data);

    int DelUserDiscuss(Long dicussId, String userId);

    int DelUserActivity(String activityId, String userId);
}
