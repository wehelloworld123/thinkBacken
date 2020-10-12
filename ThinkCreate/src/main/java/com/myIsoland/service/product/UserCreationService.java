package com.myIsoland.service.product;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.product.UserCreation;
import com.myIsoland.enums.RecomType;
import com.myIsoland.model.ResultSet;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserCreationService extends IService<UserCreation> {

    List<Map<String,Object>> GetCreatPartInfo(String createId, RecomType type,int startIndex,int pageSize);

    ResultSet<UserCreation> GetUserAdoptContent(String userId, Date date,  int start, int limit);

    List<Map<String,Object>> GetAuthorInfoByPoetryId(String poetryId,Date queryDate,int start,int limit);
}
