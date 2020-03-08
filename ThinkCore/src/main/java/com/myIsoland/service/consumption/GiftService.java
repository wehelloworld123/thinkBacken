package com.myIsoland.service.consumption;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.consumption.Gift;

public interface GiftService extends IService<Gift> {
    int SaveGift(Gift data);

    int UpdateGiftNo(String id);

    int UpdateGiftSts(String id);
}
