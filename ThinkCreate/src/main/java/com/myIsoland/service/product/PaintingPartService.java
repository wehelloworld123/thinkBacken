package com.myIsoland.service.product;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.product.PaintingPart;

import java.util.List;

public interface PaintingPartService extends IService<PaintingPart> {
    List<PaintingPart> GetPaintingPartByPaintId(String uid);

    int UpdatePart(PaintingPart data);

    PaintingPart GetCharptDetail(Long id);
}
