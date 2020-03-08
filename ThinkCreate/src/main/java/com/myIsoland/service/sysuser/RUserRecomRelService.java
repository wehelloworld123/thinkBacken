package com.myIsoland.service.sysuser;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.sysuser.RUserRecomRel;

public interface RUserRecomRelService extends IService<RUserRecomRel> {
    int SaveRUserRecom(RUserRecomRel data);
}
