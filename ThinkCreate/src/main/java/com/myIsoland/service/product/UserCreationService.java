package com.myIsoland.service.product;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.product.UserCreation;
import com.myIsoland.enums.RecomType;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserCreationService extends IService<UserCreation> {

    List<Map<String,Object>> GetCreatPartInfo(String createId, RecomType type);

    List<UserCreation> GetUserAdoptContent(String userId, Date date,int page);
}
