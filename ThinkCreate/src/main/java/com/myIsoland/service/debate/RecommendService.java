package com.myIsoland.service.debate;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.debate.Recommend;
import com.myIsoland.model.ResultSet;

import java.util.Date;
import java.util.List;

public interface RecommendService extends IService<Recommend> {
    int SaveRecommend(Recommend data);

    List<Recommend> GetUserHotRecom(String userId, String ansId, Date date);

    ResultSet<Recommend> GetUserRecomByDate(String userId, String ansId, Date date, int start, int limit);
}
