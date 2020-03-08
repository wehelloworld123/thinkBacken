package com.myIsoland.service.personal;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.lang.Snowflake;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myIsoland.common.util.SnowflakeIdWorker;
import com.myIsoland.enitity.personal.Corewall;
import com.myIsoland.mapper.personal.CorewallMapper;
import com.myIsoland.shiro.util.ShiroUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class CorewallServiceImpl extends ServiceImpl<CorewallMapper,Corewall> implements CorewallService {
    @Override
    public int SaveCorewall(Corewall data) {
        data.setUid(SnowflakeIdWorker.getUUID());
        return this.baseMapper.insert(data);
    }

    @Override
    public List<Corewall> GetCorewall(Date date, int start) {
        return this.baseMapper.queryCorewall(ShiroUtils.getUserId(),date,start);
    }


    @Override
    public List<Corewall> GetDateCorewall(String userId, DateTime startDate, DateTime endDate) {
        return this.baseMapper.selectList(new QueryWrapper<Corewall>()
                .eq("create_by",userId)
                .ge("create_dat",startDate)
                .le("create_dat",endDate)
                .orderByDesc("create_dat"));
    }
}
