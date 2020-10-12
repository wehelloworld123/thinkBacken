package com.myIsoland.service.product;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.product.LiterCharpt;
import com.myIsoland.enums.CreateKind;
import com.myIsoland.model.ResultSet;

import java.util.Date;
import java.util.List;

public interface LiterCharpService extends IService<LiterCharpt>{

    List<LiterCharpt> GetBookCharpt(String booId);

    int UpdateCharpt(LiterCharpt data);

    LiterCharpt GetCharptDetail(Long id);

    ResultSet<LiterCharpt> GetChapterByDateOrNo(Date date,int type, int startIndex, int pageSize);

    ResultSet<LiterCharpt> GetChapterByType(CreateKind kind,int startIndex,int pageSize);


    /**
     * 通过关键词查询文章章节信息，通过ela
     * @param keyword
     * @param startIndex
     * @param pageSize
     * @return
     */
    List<LiterCharpt> QueryLiterChaptByKey(String keyword, int startIndex, int pageSize);
}

