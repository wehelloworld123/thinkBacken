package com.myIsoland.service.consumption;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.consumption.Credit;

public interface CreditService extends IService<Credit> {
    /**
     * 存入积分信息
     */
    int SaveCredit(Credit data);
    /**
     * 获取用户最新的积分信息
     */
    Credit GetNewCredit(String userId);
}
