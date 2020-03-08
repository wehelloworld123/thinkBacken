package com.myIsoland.service.product;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myIsoland.enitity.product.Poetry;
import com.myIsoland.enums.CreateKind;
import com.myIsoland.mapper.product.PoetryMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PoetryServiceImpl  extends ServiceImpl<PoetryMapper,Poetry> implements PoetryService {

    @Override
    public int SavePoetry(Poetry data) {
        return this.baseMapper.insert(data);
    }

    @Override
    public int UpdatePoetry(Poetry data) {
        return this.baseMapper.updateById(data);
    }

    @Override
    public List<Poetry> GetPoetryByType(CreateKind kind, int partner, int views) {
        return this.baseMapper.selectHotLiters(kind.getValue(),partner,views);
    }

    @Override
    public Poetry GetPoetryById(String uid) {
        return this.baseMapper.selectById(uid);
    }

    @Override
    public Poetry GetTopPoetry() {
        LambdaQueryWrapper<Poetry> wrapper = new LambdaQueryWrapper();
        wrapper.eq(Poetry::getFinish,0)
                .eq(Poetry::getIsTop,1)
                .eq(Poetry::getIsDel,0)
                .orderByDesc(Poetry::getCreateDat)
                .last("LIMIT 1");
        return this.baseMapper.selectOne(wrapper);
    }
}
