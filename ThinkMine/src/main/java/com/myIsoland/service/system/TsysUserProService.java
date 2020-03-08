package com.myIsoland.service.system;

import com.baomidou.mybatisplus.extension.service.IService;

import com.myIsoland.enitity.system.TSysUserProduct;
import com.myIsoland.enums.RecomType;
import com.myIsoland.model.ProductModel;

import java.util.List;

public interface TsysUserProService extends IService<TSysUserProduct> {

    int UpdateStatus(int id, int status);

    int SaveUserProduct(TSysUserProduct data);

    List<ProductModel> GetProductList(String userId, RecomType kind, int status, int start);
}
