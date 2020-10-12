package com.myIsoland.service.product;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.product.UserProduct;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserProductService extends IService<UserProduct> {
    int DelUserProduct(String userId,Long id);

    List<Map<String,Object>> GetUserLiteratures(String userId, int kind, int type, Date date, int start, int limit);

    List<Map<String,Object>> GetUserPaintings(String userId, int kind, int type, Date date, int start, int limit);

    List<Map<String,Object>> GetUserPoemsint(String userId, int kind, int type, Date date, int start, int limit);
}
