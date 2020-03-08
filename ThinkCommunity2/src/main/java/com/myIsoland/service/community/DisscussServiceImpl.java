package com.myIsoland.service.community;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myIsoland.enitity.community.Comment;
import com.myIsoland.enitity.community.Disscuss;
import com.myIsoland.mapper.community.DisscussMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class DisscussServiceImpl extends ServiceImpl<DisscussMapper,Disscuss> implements DisscussService {

    @Override
    public List<Disscuss> GetHotDisscussByLike(String userId,int likes, Date update, int number) {
        return this.baseMapper.queryHotDiscussByLike(userId,likes,update,number);
    }


    @Override
    public List<Disscuss> GetNewDateDisscuss(String userId,Date date, int start, int number) {
        return this.baseMapper.queryNewDiscussOrderByDate(userId,date,start,number);
    }

    @Override
    public List<Disscuss> GetConcernDis(String userId, Date date, int start, int number) {
        return this.baseMapper.queryConcernDisById(userId,date,start,number);
    }

    @Override
    public Disscuss GetDiscussInfo(Long id,String userId,Date date) {
        return this.baseMapper.queryDisccussInfo(id,userId,date);
    }

    @Override
    public int updateLikeSts(String userId, Long id) {
        userId = userId + ";";
        return this.baseMapper.updateLikeSts(userId,id);
    }

    @Override
    public int batchUpdateLikes(List<Disscuss> data) {
        return this.baseMapper.batchUpdateLikeSts(data);
    }

    @Override
    public int delLikeSts(String userId, Long id) {
        userId = userId + ";";
        return this.baseMapper.delLikeSts(userId,id);
    }


    @Override
    public int SaveDisscuss(Disscuss data) {
        return this.baseMapper.insert(data);
    }

    @Override
    public List<Disscuss> GetDiscussById(String userId) {
        LambdaQueryWrapper<Disscuss> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Disscuss::getCreateBy,userId)
                .eq(Disscuss::getIsDel,0)
                .orderByDesc(Disscuss::getCreateDat)
                .last("limit 20");
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public List<Disscuss> GetUserDiscuss(String userId,Date date,int page) {
        page = page * 20;
        LambdaQueryWrapper<Disscuss> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Disscuss::getCreateBy,userId)
                .le(Disscuss::getCreateDat,date)
                .eq(Disscuss::getIsDel,0)
                .orderByDesc(Disscuss::getCreateDat)
                .last("limit "+page+", 20");
        return this.baseMapper.selectList(wrapper);
    }
}
