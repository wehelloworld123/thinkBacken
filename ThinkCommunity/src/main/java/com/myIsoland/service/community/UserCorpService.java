package com.myIsoland.service.community;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.community.UserCorportion;

public interface UserCorpService extends IService<UserCorportion> {
    int SaveUserCorp(UserCorportion data);

    int UpdateState(String userId,String corpId);

    int DelUser(String userId,String corpId);
}
