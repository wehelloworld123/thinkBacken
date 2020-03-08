package com.myIsoland.service.product;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.product.PaintContent;
import com.myIsoland.enitity.product.PaintingPart;

import java.util.Date;
import java.util.List;

public interface PaintContentService extends IService<PaintContent> {
    List<PaintContent> GetPaintContent(int partId, int start);

    int UpdatePaintContent(PaintContent data);

    int DelUserPaintContent(String userId,String no);

    int UpdateLikeSts(String userId,String no);

    PaintContent GetPaintContentById(String no);

    List<PaintContent> GetContentsOrderByDate(String userId,Long partId, Date date, List<String> arr);

    List<PaintContent> GetHotContent(String userId,Long partId);

    PaintContent GetUserAdvancePaintContent(String userId,int recomNo,int likes);

    List<PaintContent> GetUserPaintContentByDate(String userId,Date date,int page);
}
