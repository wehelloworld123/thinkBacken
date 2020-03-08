package com.myIsoland.service.community;


import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.community.UserDiscuss;

public interface UserDiscussService extends IService<UserDiscuss> {
    int SaveUserDiscuss(UserDiscuss data);

    int DelUserDiscuss(Integer dicussId,String userId);
}
