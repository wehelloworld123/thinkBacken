package com.myIsoland.service.quotation;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.quotation.Analects;

import java.util.List;

public interface AnalectService extends IService<Analects> {

    List<Analects> GetAllAnalects(String userId);

    List<Analects> GetAnalectsLike(String userId, String content);

    int SaveAnalect(Analects data);
}
