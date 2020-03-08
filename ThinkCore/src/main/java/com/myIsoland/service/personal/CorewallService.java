package com.myIsoland.service.personal;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.personal.Corewall;

import java.util.Date;
import java.util.List;

public interface CorewallService extends IService<Corewall> {
    int SaveCorewall(Corewall data);

    List<Corewall> GetCorewall(Date date, int start);

    List<Corewall> GetDateCorewall(String userId, DateTime startDate,DateTime endDate);
}
