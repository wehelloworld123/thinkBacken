package com.myIsoland.service.community;


import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myIsoland.enitity.community.Activity;
import com.myIsoland.mapper.community.ActivityMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper,Activity> implements ActivityService {


    @Override
    public List<Activity> GetActivityByCorpId(String corpId, Date date, int page) {
        return this.baseMapper.queryCorpActivity(corpId,date,page,3);
    }

    @Override
    public List<Activity> GetHotActivity(Date update, int views) {
        return this.baseMapper.queryHotActivity(update,views,12);
    }

    @Override
    public List<Activity> GetNewActivity(int number) {
        return this.baseMapper.queryNewActivity(number);
    }

    @Override
    public List<Activity> GetNewActivityByDate(DateTime date, int number) {
        return this.baseMapper.queryNewDateActivity(date,number);
    }

    @Override
    public List<Activity> GetConcernActvity(String userId, Date date, int page) {
        page = page * 12;
        return this.baseMapper.queryConcernActivity(userId,date,page,12);
    }

    @Override
    public List<Activity> GetRefreshHotActvity(Date update, int views) {
        return this.baseMapper.queryHotActivity(update,views,12);
    }

    @Override
    public List<Activity> GetRefreshConActvity(String userId, Date date, int page) {
        page = page * 12;
        return this.baseMapper.queryConcernActivity(userId,date,page,12);
    }


}
