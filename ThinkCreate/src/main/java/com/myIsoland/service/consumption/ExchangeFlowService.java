package com.myIsoland.service.consumption;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.consumption.ExchangeFlow;

public interface ExchangeFlowService extends IService<ExchangeFlow> {
    int SaveExchangeFlow(ExchangeFlow data);

    ExchangeFlow GetExchangeFlow(String flowId);

    int ComplExFlowSts(String flowId);

    int InvalidExFlowSts(String flowId);
}
