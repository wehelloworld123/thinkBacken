package com.myIsoland.service.consumption;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myIsoland.enitity.consumption.Credit;
import com.myIsoland.mapper.consumption.CreditMapper;
import org.springframework.stereotype.Service;

@Service
public class CreditServiceImpl extends ServiceImpl<CreditMapper,Credit> implements CreditService {
    @Override
    public int SaveCredit(Credit data) {
        return this.baseMapper.insert(data);
    }

    @Override
    public Credit GetNewCredit(String userId) {
        return this.baseMapper.selectOne(new QueryWrapper<Credit>().eq("create_by",userId).eq("id_del",0).orderByDesc("create_dat"));
    }
}
