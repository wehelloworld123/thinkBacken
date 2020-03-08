package com.myIsoland.service.system;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myIsoland.enitity.system.TSysUserProduct;
import com.myIsoland.enums.RecomType;
import com.myIsoland.mapper.system.TsysUserProMapper;
import com.myIsoland.model.ProductModel;

import java.util.List;

public class TsysUserProServiceImpl extends ServiceImpl<TsysUserProMapper,TSysUserProduct> implements TsysUserProService {
    @Override
    public int UpdateStatus(int id,int status) {
        TSysUserProduct data = new TSysUserProduct();
        data.setId(id);
        data.setStatus(status);
        return this.baseMapper.updateById(data);
    }

    @Override
    public int SaveUserProduct(TSysUserProduct data) {
        return this.baseMapper.insert(data);
    }

    @Override
    public List<ProductModel> GetProductList(String userId, RecomType kind, int status, int start) {
        if(kind == RecomType.LITERATURE){
            return this.baseMapper.selectUserLiter(userId,kind.getValue(),status,start);
        }
        else if(kind == RecomType.PAINTING){
            return this.baseMapper.selectUserPaint(userId,kind.getValue(),status,start);
        }
        else return null;

    }
}
