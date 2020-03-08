package com.myIsoland.service.debate;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.debate.Recommend;

import java.util.Date;
import java.util.List;

public interface RecommendService extends IService<Recommend> {
    int SaveRecommend(Recommend data);

    List<Recommend> GetNextRecom(String ansId, Date date);
}
