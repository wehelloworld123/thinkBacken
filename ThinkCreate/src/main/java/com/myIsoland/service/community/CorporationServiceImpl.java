package com.myIsoland.service.community;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
        LambdaQueryWrapper<Corporation> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Corporation::getIsDel,0)
                .select(Corporation::getId, Corporation::getName, Corporation::getLikes,
                        Corporation::getStar, Corporation::getLogo, Corporation::getLabel, Corporation::getCreateDat);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public int SaveLikeCorp(String userId,String id) {
        userId = userId + ";";
        return this.baseMapper.updateCorpLike(userId,id);
    }

    @Override
    public List<TsysUser> GetCorpUser(String corpId) {
        return this.baseMapper.selectCorpUser(corpId);
    }

    @Override
    public Corporation GetCorpInfo(String corpId, String userId) {

        return this.baseMapper.selectCorpById(userId,corpId);
    }
}
