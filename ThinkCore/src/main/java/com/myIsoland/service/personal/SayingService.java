package com.myIsoland.service.personal;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.personal.Saying;

import java.util.Date;
import java.util.List;

public interface SayingService extends IService<Saying> {
    List<Saying> GetSayingList(Date date);

    int DeleteSaying(int id);

}
