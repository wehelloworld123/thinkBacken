package com.myIsoland.service.product;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myIsoland.enitity.product.UserProduct;
import com.myIsoland.mapper.product.UserProductMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class UserProductServiceImpl extends ServiceImpl<UserProductMapper,UserProduct> implements UserProductService {
    @Override
    public int DelUserProduct(String userId, Long id) {
        LambdaQueryWrapper<UserProduct> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserProduct::getUserId,userId)
                .eq(UserProduct::getId,id)
                .eq(UserProduct::getIsDel,0);
        return this.baseMapper.delete(wrapper);
    }

    @Override
    public List<UserProduct> GetUserLiteratures(String userId,int kind, Date date, int page) {
        return this.baseMapper.selectUserLiterProductByTyp(userId,kind,date,page);
    }

    @Override
    public List<UserProduct> GetUserPaintings(String userId,int kind, Date date, int page) {
        return this.baseMapper.selectUserPaintProductByTyp(userId,kind,date,page);
    }

    @Override
    public List<UserProduct> GetUserPoemsint(String userId,int kind, Date date, int page) {
        return this.baseMapper.selectUserPoemProductByTyp(userId,kind,date,page);
    }
}
