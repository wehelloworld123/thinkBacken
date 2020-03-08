package com.myIsoland.service.quotation;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myIsoland.enitity.quotation.Analects;
import com.myIsoland.mapper.quotation.AnalectMapper;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class AnalectServiceImpl extends ServiceImpl<AnalectMapper,Analects> implements AnalectService {
    @Override
    public List<Analects> GetAllAnalects(String userId) {
        return this.baseMapper.selectList(new QueryWrapper<Analects>().eq("create_by",userId));
    }

    @Override
    public List<Analects> GetAnalectsLike(String userId, String content) {
        return this.baseMapper.selectList(new QueryWrapper<Analects>().eq("create_by",userId).like(true,"title",content));
    }

    @Override
    public int SaveAnalect(Analects data) {
        return this.baseMapper.insert(data);
    }
}