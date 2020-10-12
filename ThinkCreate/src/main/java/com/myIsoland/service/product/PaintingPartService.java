package com.myIsoland.service.product;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.product.PaintingPart;
import com.myIsoland.enums.CreateEnum;
import com.myIsoland.model.ResultSet;

import java.util.Date;
import java.util.List;

public interface PaintingPartService extends IService<PaintingPart> {
    List<PaintingPart> GetPaintingPartByPaintId(String uid);

    int UpdatePart(PaintingPart data);

    PaintingPart GetCharptDetail(Long id);



    ResultSet<PaintingPart> GetPartByDateOrNo(Date date, int type, int startIndex, int pageSize);

    ResultSet<PaintingPart> GetPartByType(CreateEnum kind, int startIndex, int pageSize);

    /**
     * 通过关键词查询绘画小节信息，通过ela
     * @param keyword
     * @param startIndex
     * @param pageSize
     * @return
     */
    List<PaintingPart> QueryPaintingPartByKey(String keyword, int startIndex, int pageSize);
}
