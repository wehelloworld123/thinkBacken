package com.myIsoland.service.debate;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myIsoland.common.base.BaseEntity;
import com.myIsoland.common.util.CaculateUtils;
import com.myIsoland.constant.ProjectConstant;
import com.myIsoland.constant.THINKConstant;
import com.myIsoland.enitity.debate.Recommend;
import com.myIsoland.mapper.debate.RecommendMapper;
import com.myIsoland.model.ResultSet;
import com.myIsoland.shiro.util.ShiroUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class RecommendServiceImpl extends ServiceImpl<RecommendMapper,Recommend> implements RecommendService {
    @Override
    public int SaveRecommend(Recommend data) {
        data.setCreator(ShiroUtils.getUser().getUsername());
        return this.baseMapper.insert(data);
    }

    @Override
    public List<Recommend> GetUserHotRecom(String useId,String ansId, Date date) {
        QueryWrapper<Recommend> wrapper = new QueryWrapper<>();
        wrapper.select("id","content","likes","creator","create_by as createBy","create_dat as createDat")
                .lambda()
                .eq(Recommend::getAnsId,ansId)
                .le(BaseEntity::getCreateDat,date)
                .eq(BaseEntity::getIsDel,0)
                .orderByDesc(BaseEntity::getCreateDat)
                .last("limit 3");

        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public ResultSet<Recommend> GetUserRecomByDate(String userId, String ansId, Date date, int start, int limit) {
        ansId= ProjectConstant.ANSPREFIX + ansId;
        PageHelper.offsetPage(start,limit,true);
        QueryWrapper<Recommend> wrapper = new QueryWrapper<>();
        wrapper.select("id","content","likes","creator","create_by as createBy","create_dat as createDat")
                .lambda()
                .eq(Recommend::getAnsId,ansId)
                .le(Recommend::getCreateDat,date)
                .eq(Recommend::getIsDel,1)
                .orderByDesc(BaseEntity::getCreateDat);
        List<Recommend> list = this.baseMapper.selectList(wrapper);
        list = deleteTopicsPrefix(list);
        PageInfo<Recommend> pageInfo = new PageInfo<>(list);
        ResultSet<Recommend> resultSet = new ResultSet<>();
        resultSet.setList(list);
        resultSet.setCount(pageInfo.getTotal());


        return  resultSet;
    }

    private List<Recommend> deleteTopicsPrefix(List<Recommend> data){
        for(Recommend item :data){
            item.setAnsId(CaculateUtils.translateId(ProjectConstant.ANSPREFIX,item.getAnsId()));
            item.setCreateBy(CaculateUtils.translateId(ProjectConstant.USERPREFIX,item.getCreateBy()));
        }
        return data;
    }
}
