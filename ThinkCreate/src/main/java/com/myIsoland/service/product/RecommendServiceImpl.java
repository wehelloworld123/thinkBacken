package com.myIsoland.service.product;

import com.alibaba.druid.sql.visitor.functions.Substring;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myIsoland.common.base.BaseEntity;
import com.myIsoland.constant.ProjectConstant;
import com.myIsoland.enitity.product.Recommend;;
import com.myIsoland.enums.RecomType;
import com.myIsoland.mapper.product.RecommendMapper;
import com.myIsoland.model.ResultSet;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RecommendServiceImpl extends ServiceImpl<RecommendMapper,Recommend> implements RecommendService {
    @Override
    public int SaveRecomment(Recommend data) {
        return this.baseMapper.insert(data);
    }

    @Override
    public int UpdateLikeSts(String userId, Long id) {
        userId = userId + ";";
        return this.baseMapper.updateLikeSts(userId,id);
    }

    @Override
    public int DelLikeSts(String userId, Long id) {
        userId = userId + ";";
        return this.baseMapper.delLikeSts(userId,id);
    }

    @Override
    public int UpdateRecomment(Recommend data) {
        return this.baseMapper.updateById(data);
    }

    @Override
    public List<Recommend> GetRecommentByContentId(String id,int start) {

        PageHelper.offsetPage(start,15,true);
        return this.baseMapper.selectList(new QueryWrapper<Recommend>().eq("content_id",id).eq("is_del",0).orderByDesc("create_dat"));

    }

    @Override
    public List<Recommend> GetRecommentByDate(String userId,String no, Date date, RecomType type, int start,int limit) {
        return this.baseMapper.selectRecommendByDate(userId,no,date,type.getValue(),start,limit);
    }

    @Override
    public ResultSet<Recommend> GetUserRecommentByDate(String userId, Date date, RecomType type, int start, int limit) {
        PageHelper.offsetPage(start,limit,true);
        QueryWrapper<Recommend> wrapper = new QueryWrapper<>();
        wrapper.select("id","title","substring(content,1,200) as content","summary","typ as kind","contentId","adopt","create_dat as createDat")
                .lambda()
                .eq(Recommend::getCreateBy,userId)
                .eq(Recommend::getKind,type.getValue())
                .le(Recommend::getCreateDat,date)
                .eq(Recommend::getAdopt,1);
        List<Recommend> list = this.baseMapper.selectList(wrapper);
        PageInfo<Recommend> pageInfo = new PageInfo<>(list);
        ResultSet<Recommend> resultSet = new ResultSet<>();
        resultSet.setList(list);
        resultSet.setCount(pageInfo.getTotal());
        return  resultSet;
    }
}
