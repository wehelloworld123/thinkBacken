package com.myIsoland.service.product;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myIsoland.annotation.DataSource;
import com.myIsoland.constant.ProjectConstant;
import com.myIsoland.enitity.product.Recommend;
import com.myIsoland.enums.DataSourceEnum;
import com.myIsoland.enums.RecomType;
import com.myIsoland.mapper.product.RecommendMapper;
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
    public int UpdateRecomment(Recommend data) {
        return this.baseMapper.updateById(data);
    }

    @Override
    public List<Recommend> GetRecommentByContentId(int id,int start) {
        IPage<Recommend> curPage = new Page<>(start,15);
        curPage = this.baseMapper.selectPage(curPage,new QueryWrapper<Recommend>().eq("content_id",id).eq("is_del",0).orderByDesc("create_dat"));
        return curPage.getRecords();
    }

    @Override
    public List<Recommend> GetRecommentByDate(String no, Date date, RecomType type, int start) {
        if (RecomType.LITERATURE.equals(type)){
            no = ProjectConstant.CLITERPREFIX + no;
        }else if(RecomType.PAINTING.equals(type)){
            no = ProjectConstant.CLITERPREFIX + no;
        }else if(RecomType.POEMTY.equals(type)){
            no = ProjectConstant.CLITERPREFIX + no;
        }
        return this.baseMapper.selectRecommendByDate(no,date,type.getValue(),start);
    }
}
