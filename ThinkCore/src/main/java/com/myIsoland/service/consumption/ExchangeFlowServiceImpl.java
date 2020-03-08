package com.myIsoland.service.consumption;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myIsoland.enitity.consumption.ExchangeFlow;
import com.myIsoland.mapper.consumption.ExchangeFlowMapper;
import org.springframework.stereotype.Service;

@Service
public class ExchangeFlowServiceImpl extends ServiceImpl<ExchangeFlowMapper,ExchangeFlow> implements ExchangeFlowService {
    @Override
    public int SaveExchangeFlow(ExchangeFlow data) {
        return this.baseMapper.insert(data);
    }

    @Override
    public ExchangeFlow GetExchangeFlow(String flowId) {
        return this.baseMapper.selectOne(new QueryWrapper<ExchangeFlow>().eq("trace_id",flowId).eq("finish",0).eq("is_del",0));
    }

    @Override
    public int ComplExFlowSts(String flowId) {
        ExchangeFlow data = new ExchangeFlow();
        data.setTraceNo(flowId);
        data.setFinish(1);
        return this.baseMapper.updateById(data);
    }

    @Override
    public int InvalidExFlowSts(String flowId) {
        ExchangeFlow data = new ExchangeFlow();
        data.setTraceNo(flowId);
        data.setFinish(-1);
        return this.baseMapper.updateById(data);
    }
}
