package com.myIsoland.service.debate;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myIsoland.common.util.CaculateUtils;
import com.myIsoland.constant.ProjectConstant;
import com.myIsoland.constant.THINKConstant;
import com.myIsoland.enitity.debate.Recommend;
import com.myIsoland.mapper.debate.RecommendMapper;
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
    public List<Recommend> GetNextRecom(String ansId, Date date) {
        ansId = ProjectConstant.ANSPREFIX + ansId;
        IPage<Recommend> curPage=new Page<>(1,15);
        LambdaQueryWrapper<Recommend> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Recommend::getParentId,ansId)
                .lt(Recommend::getCreateDat,date)
                .orderByDesc(Recommend::getCreateDat)
                .last("LIMIT 15")
                .select(Recommend::getId,Recommend::getCreator,Recommend::getContent,Recommend::getCreateDat);
        return  deleteTopicsPrefix(this.baseMapper.selectList(wrapper));
    }

    private List<Recommend> deleteTopicsPrefix(List<Recommend> data){
        for(Recommend item :data){
            item.setCreateBy(CaculateUtils.translateId(ProjectConstant.USERPREFIX,item.getCreateBy()));
        }
        return data;
    }
}
