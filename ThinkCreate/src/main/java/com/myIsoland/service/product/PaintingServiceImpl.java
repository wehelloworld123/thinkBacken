package com.myIsoland.service.product;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myIsoland.enitity.product.Painting;
import com.myIsoland.enums.CreateKind;
import com.myIsoland.mapper.product.PaintingMapper;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;
@Service
public class PaintingServiceImpl extends ServiceImpl<PaintingMapper,Painting> implements PaintingService{
    @Override
    public int SavePainting(Painting data) {
        return this.baseMapper.insert(data);
    }

    @Override
    public int UpdatePainting(Painting data) {
        return this.baseMapper.updateById(data);
    }

    @Override
    public List<Painting> GetPaintingByType(CreateKind kind, int partner,int views) {
        return this.baseMapper.selectPaintByType(kind.getValue(),partner,views);
    }

    @Override
    public Painting GetPaintingById(String uid) {
        LambdaQueryWrapper<Painting> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Painting::getUid,uid)
                .eq(Painting::getIsDel,0);
        return this.baseMapper.selectOne(wrapper);
    }

    @Override
    public Painting GetTopPainting() {
        LambdaQueryWrapper<Painting> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Painting::getIsTop,1)
                .eq(Painting::getFinish,0)
                .eq(Painting::getIsDel,0)
                .orderByDesc(Painting::getCreateDat)
                .last("limit 1");
        return this.baseMapper.selectOne(wrapper);
    }
}
