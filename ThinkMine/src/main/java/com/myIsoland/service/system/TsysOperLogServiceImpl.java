package com.myIsoland.service.system;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myIsoland.enitity.system.TsysOperLog;
import com.myIsoland.mapper.system.TsysOperLogMapper;
import org.springframework.stereotype.Service;

@Service
public class TsysOperLogServiceImpl extends ServiceImpl<TsysOperLogMapper,TsysOperLog> implements TsysOperLogService {
    public int insertSelective(TsysOperLog recode){
        return this.baseMapper.insert(recode);
    }
}
