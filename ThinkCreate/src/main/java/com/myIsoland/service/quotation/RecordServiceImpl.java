package com.myIsoland.service.quotation;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myIsoland.enitity.quotation.Record;
import com.myIsoland.mapper.quotation.RecordMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper,Record> implements RecordService {
    @Override
    public List<Record> GetQuoRecord(String analId,int start) {
        IPage<Record> curPage = new Page<>(start,30);
        curPage = this.baseMapper.selectPage(curPage,new QueryWrapper<Record>().eq("anal_id",analId));
        return curPage.getRecords();
    }

    @Override
    public int SaveRecord(Record data) {
        return this.baseMapper.insert(data);
    }
}
