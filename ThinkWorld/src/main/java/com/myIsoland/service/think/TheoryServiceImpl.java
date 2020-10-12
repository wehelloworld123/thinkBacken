package com.myIsoland.service.think;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myIsoland.common.base.BaseEntity;
import com.myIsoland.enitity.think.Theory;
import com.myIsoland.mapper.think.TheoryMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TheoryServiceImpl extends ServiceImpl<TheoryMapper,Theory> implements TheoryService {

    @Override
    public int SaveTheory(Theory data) {
        return this.baseMapper.insert(data);
    }

    @Override
    public int UpdateTheory(Theory data) {
        UpdateWrapper<Theory> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("createBy",data.getCreateBy()).eq("is_del",0);
        return this.baseMapper.update(data,updateWrapper);
    }

    @Override
    public int UpdateTheoryByUserId(int islock,String userid) {
        Theory data = new Theory();
        UpdateWrapper<Theory> wrapper = new UpdateWrapper<>();
        wrapper.set("is_lock",islock)
                .lambda()
                .eq(BaseEntity::getCreateBy,userid);
        return this.baseMapper.update(data,wrapper);
    }

    @Override
    public List<Theory> GetTheoryByUserId(String userId,int lock) {
        LambdaQueryWrapper<Theory> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Theory::getCreateBy,userId)
                .eq(Theory::getIsLock,lock)
                .eq(Theory::getIsDel,0)
        .orderByDesc(Theory::getCreateDat);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public List<Theory> GetUserTheory(String userId) {
        LambdaQueryWrapper<Theory> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Theory::getCreateBy,userId)
                .eq(Theory::getIsDel,0)
                .orderByDesc(Theory::getCreateDat);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public int UpdateTheoryView(int id) {
        return this.baseMapper.UpdateTheorytView(id);
    }


}
