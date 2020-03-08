package com.myIsoland.service.product;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myIsoland.enitity.product.PaintingPart;
import com.myIsoland.mapper.product.PaintingPartMapper;
import org.springframework.stereotype.Service;

import java.awt.image.ImageConsumer;
import java.util.List;
@Service
public class PaintingPartServiceImpl extends ServiceImpl<PaintingPartMapper,PaintingPart> implements PaintingPartService {

    @Override
    public List<PaintingPart> GetPaintingPartByPaintId(String uid) {
        return this.baseMapper.selectPaintingParts(uid);
    }

    @Override
    public int UpdatePart(PaintingPart data) {
        return this.baseMapper.updateById(data);
    }

    @Override
    public PaintingPart GetCharptDetail(Long id) {
        return null;
    }
}
