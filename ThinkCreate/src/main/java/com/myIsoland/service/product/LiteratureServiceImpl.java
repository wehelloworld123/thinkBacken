package com.myIsoland.service.product;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myIsoland.enitity.product.Literature;
import com.myIsoland.enums.CreateKind;
import com.myIsoland.mapper.product.LiteratureMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LiteratureServiceImpl extends ServiceImpl<LiteratureMapper,Literature> implements LiteratureService {
    @Override
    public int SaveLiterature(Literature data) {

        return this.baseMapper.insert(data);
    }

    @Override
    public int UpdateLiterature(Literature data) {
        return this.baseMapper.updateById(data);
    }

    @Override
    public List<Literature> GetLiteratureByType(CreateKind kind,int partner,int views) {
        return this.baseMapper.selectHotLiters(kind.getValue(),partner,views);
    }

    @Override
    public List<Literature> GetInitLiterByType(CreateKind kind, int partner, int views) {
        return this.baseMapper.selectHotLiters(kind.getValue(),partner,views);
    }

    @Override
    public Literature GetLiteratureById(String uid) {
        return this.baseMapper.selectById(uid);
    }

    @Override
    public List<Map<String,Object>> GetAdvanceLiterature() {
        LambdaQueryWrapper<Literature> wrapper = new LambdaQueryWrapper();
        wrapper.eq(Literature::getFinish,-1)
                .eq(Literature::getIsTop,1)
                .eq(Literature::getIsDel,0)
                .orderByDesc(Literature::getCreateDat)
                .last("LIMIT 7")
                .select(Literature::getUid,Literature::getCover);
        return this.baseMapper.selectMaps(wrapper);
    }

    @Override
    public List<Literature> GetHotIpLiteratures(int partner,int views) {
        LambdaQueryWrapper<Literature> wrapper = new LambdaQueryWrapper();
        wrapper
                .ge(Literature::getPartner,partner)
                .ge(Literature::getViews,views)
                .eq(Literature::getFinish,0)
                .eq(Literature::getIsDel,0)
                .orderByDesc(Literature::getCreateDat)
                .select(Literature::getUid,Literature::getName,Literature::getCover,Literature::getCreateDat);
        return this.baseMapper.selectList(wrapper);
    }


}
