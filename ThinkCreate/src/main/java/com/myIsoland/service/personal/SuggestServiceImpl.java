package com.myIsoland.service.personal;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myIsoland.enitity.personal.Suggestion;
import com.myIsoland.mapper.personal.SuggestMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuggestServiceImpl extends ServiceImpl<SuggestMapper,Suggestion> implements SuggestService {
    @Override
    public int SaveSuggestion(Suggestion data) {
        return this.baseMapper.insert(data);
    }

    @Override
    public List<Suggestion> GetNewSuggestions(int start) {
        IPage<Suggestion> curPage = new Page<>(start,20);
        curPage = this.baseMapper.selectPage(curPage,new QueryWrapper<Suggestion>().orderByDesc("create_dat"));
        return curPage.getRecords();
    }

    @Override
    public List<Suggestion> GetOldSuggestions(int start) {
        IPage<Suggestion> curPage = new Page<>(start,20);
        curPage = this.baseMapper.selectPage(curPage,new QueryWrapper<Suggestion>().orderByAsc("create_dat"));
        return curPage.getRecords();
    }
}
