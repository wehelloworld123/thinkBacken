package com.myIsoland.service.search;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.myIsoland.enitity.search.SearchRecord;
import com.myIsoland.mapper.search.SearchMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class SearchServiceImpl  extends ServiceImpl<SearchMapper,SearchRecord> implements SearchService {
    @Override
    public List<SearchRecord> selectAllKeysByDateOrCount(Date startDate, Date endDate, Long count,int start,int limit) {
        PageHelper.offsetPage(start, limit, true);
        List<SearchRecord> list = this.baseMapper.selectAllKeysByDateOrCount(startDate,endDate,count);
        return list;
    }
}
