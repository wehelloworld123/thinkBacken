package com.myIsoland.service.sysuser;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.sysuser.RUserlikeRel;
import com.myIsoland.mapper.sysuser.RUserlikeRelMapper;

public interface RUserlikeRelService extends IService<RUserlikeRel> {
    int SaveRUserlike(RUserlikeRel data);
}
