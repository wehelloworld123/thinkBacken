package com.myIsoland.service.product;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.product.UserProduct;

import java.util.Date;
import java.util.List;

public interface UserProductService extends IService<UserProduct> {
    int DelUserProduct(String userId,Long id);

    List<UserProduct> GetUserLiteratures(String userId,int kind, Date date, int page);

    List<UserProduct> GetUserPaintings(String userId,int kind, Date date, int page);

    List<UserProduct> GetUserPoemsint(String userId,int kind, Date date, int page);
}
