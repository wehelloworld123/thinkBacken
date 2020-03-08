package com.myIsoland.service.community;

import cn.hutool.core.date.DateTime;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.community.Disscuss;
import com.myIsoland.enitity.community.UserDiscuss;

import java.util.Date;
import java.util.List;

public interface DisscussService extends IService<Disscuss> {

    List<Disscuss> GetHotDisscuss();

    List<Disscuss> GetHotDisscuss(int like);

    List<Disscuss> GetNewDisscuss();

    List<Disscuss> GetNewDisscuss(DateTime date);

    List<Disscuss> GetConcernDis(String userId);

    List<Disscuss> GetConcernDis(String userId,DateTime dateTime);


    int SaveDisscuss(Disscuss data);
}
