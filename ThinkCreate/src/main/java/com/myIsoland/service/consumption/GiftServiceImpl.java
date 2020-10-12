package com.myIsoland.service.consumption;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myIsoland.enitity.consumption.Gift;
import com.myIsoland.mapper.consumption.GiftMapper;
import org.springframework.stereotype.Service;

@Service
public class GiftServiceImpl extends ServiceImpl<GiftMapper,Gift> implements GiftService {
    @Override
    public int SaveGift(Gift data) {
        return this.baseMapper.insert(data);
    }

    @Override
    public int UpdateGiftNo(String id) {
        return this.baseMapper.updateNo(id);
    }

    @Override
    public int UpdateGiftSts(String id) {
        Gift data = new Gift();
        data.setUid(id);
        data.setFinish(1);
        return this.baseMapper.updateById(data);
    }
}
