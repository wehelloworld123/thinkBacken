package com.myIsoland.service.product;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myIsoland.common.util.CaculateUtils;
import com.myIsoland.constant.ProjectConstant;
import com.myIsoland.enitity.product.LiterCharpt;
import com.myIsoland.enitity.product.Literature;
import com.myIsoland.mapper.product.LiterCharptMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LiterCharpServiceImpl extends ServiceImpl<LiterCharptMapper,LiterCharpt> implements LiterCharpService {
    @Override
    public List<LiterCharpt> GetBookCharpt(String bookId) {
        return this.baseMapper.selectBookCharps(bookId);
    }
    @Override
    public int UpdateCharpt(LiterCharpt data) {
        return this.baseMapper.updateById(data);
    }

    @Override
    public LiterCharpt GetCharptDetail(Long id) {
        return this.baseMapper.selectChaptById(id);
    }


}
