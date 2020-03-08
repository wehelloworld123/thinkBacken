package com.myIsoland.service.product;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.product.Recommend;
import com.myIsoland.enums.RecomType;

import java.util.Date;
import java.util.List;

public interface RecommendService extends IService<Recommend> {

    int SaveRecomment(Recommend data);

    int UpdateLikeSts(String userId,Long id);

    int UpdateRecomment(Recommend data);

    List<Recommend> GetRecommentByContentId(int id,int start);

    List<Recommend> GetRecommentByDate(String no, Date date, RecomType type, int start);
}
