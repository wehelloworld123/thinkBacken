package com.myIsoland.service.product;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myIsoland.enitity.product.PoemSet;
import com.myIsoland.mapper.product.PoemSetMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PoemSetServiceImpl  extends ServiceImpl<PoemSetMapper,PoemSet> implements PoemSetService {
    @Override
    public List<PoemSet> GetPoemSetByPoetryId(String uid) {
        return this.baseMapper.selectPoemSets(uid);
    }

    @Override
    public int UpdatePoemSet(PoemSet data) {
        return this.baseMapper.updateById(data);
    }

    @Override
    public PoemSet GetPoemSetDetail(Long id) {
        return this.baseMapper.selectChaptById(id);
    }
}
