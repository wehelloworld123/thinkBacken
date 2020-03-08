package com.myIsoland.service.think;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myIsoland.enitity.think.Quotation;
import com.myIsoland.enitity.think.Theory;
import com.myIsoland.mapper.think.QuotationMapper;
import com.myIsoland.mapper.think.TheoryMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuotationServiceImpl extends ServiceImpl<QuotationMapper,Quotation> implements QuotationService {
    @Override
    public List<Quotation> GetQuotationByUserId(String userId, String theoryNo,int lock) {
        LambdaQueryWrapper<Quotation> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Quotation::getCreateBy,userId)
                .eq(Quotation::getTheoryNo,theoryNo)
                .eq(Quotation::getIsLock,lock)
                .eq(Quotation::getIsDel,0)
                .orderByDesc(Quotation::getCreateDat)
                .select(Quotation::getId,Quotation::getContent,Quotation::getViews,Quotation::getCreateDat,Quotation::getLUpdateDat);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public List<Quotation> GetUserQuotation(String userId, String theoryNo) {
        LambdaQueryWrapper<Quotation> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Quotation::getCreateBy,userId)
                .eq(Quotation::getTheoryNo,theoryNo)
                .eq(Quotation::getIsDel,0)
                .orderByDesc(Quotation::getCreateDat)
                .select(Quotation::getId,Quotation::getContent,Quotation::getViews,Quotation::getCreateDat,Quotation::getLUpdateDat);
        return this.baseMapper.selectList(wrapper);
    }
    @Override
    public List<Quotation> GetUserQuotation(String userId, String theoryNo,int page) {
        IPage<Quotation> curPage = new Page<>(page,10);
        LambdaQueryWrapper<Quotation> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Quotation::getCreateBy,userId)
                .eq(Quotation::getTheoryNo,theoryNo)
                .eq(Quotation::getIsDel,0)
                .select(Quotation::getId,Quotation::getContent,Quotation::getCreateDat,Quotation::getLUpdateDat);
        return this.baseMapper.selectPage(curPage,wrapper).getRecords();
    }

    @Override
    public Quotation GetQuotationById(int id,int lock) {
        LambdaQueryWrapper<Quotation> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Quotation::getId,id)
                .eq(Quotation::getIsLock,lock)
                .eq(Quotation::getIsDel,0);
        return this.baseMapper.selectOne(wrapper);
    }

    @Override
    public int SaveQuotation(Quotation data) {
        return this.baseMapper.insert(data);
    }

    @Override
    public int UpdateQuotationById(Quotation data) {
        return this.baseMapper.updateById(data);
    }

    @Override
    public int UpdateQuotationView(int id) {
        return this.baseMapper.UpdateQuotView(id);
    }
}