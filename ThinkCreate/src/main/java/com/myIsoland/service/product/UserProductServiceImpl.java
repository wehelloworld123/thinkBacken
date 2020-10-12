package com.myIsoland.service.product;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myIsoland.enitity.product.UserProduct;
import com.myIsoland.mapper.product.UserProductMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
    public List<Map<String,Object>> GetUserLiteratures(String userId, int kind, int type, Date date, int start, int limit) {
        return this.baseMapper.selectUserLiterProductByTyp(userId,kind,type,date,start,limit);
    }

    @Override
    public List<Map<String,Object>> GetUserPaintings(String userId, int kind, int type, Date date, int start, int limit) {
        return this.baseMapper.selectUserPaintProductByTyp(userId,kind,type,date,start,limit);
    }

    @Override
    public List<Map<String,Object>> GetUserPoemsint(String userId, int kind, int type, Date date, int start, int limit) {
        return this.baseMapper.selectUserPoemProductByTyp(userId,kind,type,date,start,limit);
    }
}
