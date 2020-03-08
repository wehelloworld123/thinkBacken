package com.myIsoland.service.product;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.product.PoemContent;

import java.util.Date;
import java.util.List;

public interface PoemContentService extends IService<PoemContent> {


    int UpdatePoemContent(PoemContent data);

    int UpdateLikeSts(String userId,String no);

    int DelUserPoemContent(String userId,String no);

    PoemContent GetPoemContentById(String no);

    List<PoemContent> GetContentsOrderByDate(String userId,Long charptId, Date date, List<String> arr);

    List<PoemContent> GetHotPoemContent(String userId,Long charptId);

    PoemContent GetUserAdvancePoemContent(String userId,int recomNo,int likes);

    List<PoemContent> GetUserPoemContentByDate(String userId,Date date,int page);
}
