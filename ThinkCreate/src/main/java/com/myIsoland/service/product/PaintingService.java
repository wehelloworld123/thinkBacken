package com.myIsoland.service.product;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.product.Painting;
import com.myIsoland.enums.CreateKind;

import java.util.List;

public interface PaintingService extends IService<Painting> {
    int SavePainting(Painting data);

    int UpdatePainting(Painting data);

    List<Painting> GetPaintingByType(CreateKind kind, int partner,int views);

    Painting GetPaintingById(String uid);

    Painting GetTopPainting();
}
