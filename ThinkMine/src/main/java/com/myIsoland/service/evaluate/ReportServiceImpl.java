package com.myIsoland.service.evaluate;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myIsoland.enitity.evaluate.Report;
import com.myIsoland.mapper.evalute.ReportMapper;
import com.myIsoland.model.ReportModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl extends ServiceImpl<ReportMapper,Report> implements ReportService {
    @Override
    public int SaveReport(Report data) {
        return this.baseMapper.insert(data);
    }

    @Override
    public List<Report> GetReports(String userId, int page) {
        IPage<Report> curPage = new Page<>(page,10);
        LambdaQueryWrapper<Report> wrapper = new LambdaQueryWrapper();
        wrapper .eq(Report::getCreateBy,userId)
                .orderByDesc(Report::getCreateDat)
                .select(Report::getNo,Report::getName,Report::getCreateDat);
        curPage = this.baseMapper.selectPage(curPage,wrapper);
        return curPage.getRecords();
    }

    @Override
    public Report GetReportById(String id) {
        return this.baseMapper.selectById(id);
    }
}
