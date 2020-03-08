package com.myIsoland.service.community;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myIsoland.enitity.community.Corporation;
import com.myIsoland.enitity.system.TsysUser;
import com.myIsoland.mapper.community.CorporationMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CorporationServiceImpl extends ServiceImpl<CorporationMapper,Corporation> implements CorporationService{

    private double corporation;
    private double Co;
    private boolean isDel;

    @Override
    public List<Corporation> GetCorporation() {
        // 条件构建器 T为entity
        QueryWrapper<Corporation> queryWrapper = new QueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper.eq("is_del",0));
    }

    @Override
    public int SaveLikeCorp(String id) {
        return this.baseMapper.updateCorpLike(id);
    }

    @Override
    public List<TsysUser> GetCorpUser(String corpId) {
        return this.baseMapper.selectCorpUser(corpId);
    }
}
