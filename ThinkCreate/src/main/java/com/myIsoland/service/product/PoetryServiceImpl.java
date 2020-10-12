package com.myIsoland.service.product;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myIsoland.common.base.BaseEntity;
import com.myIsoland.dao.PoetryESRepository;
import com.myIsoland.enitity.product.LiterContent;
import com.myIsoland.enitity.product.Poetry;
import com.myIsoland.enums.CreateEnum;
import com.myIsoland.mapper.product.PoetryMapper;
import io.netty.util.internal.StringUtil;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class PoetryServiceImpl  extends ServiceImpl<PoetryMapper,Poetry> implements PoetryService {
    @Autowired
    private PoetryESRepository poetryESRepository;

    @Override
    public int SavePoetry(Poetry data) {
        return this.baseMapper.insert(data);
    }

    @Override
    public int UpdatePoetry(Poetry data) {
        return this.baseMapper.updateById(data);
    }

    @Override
    public List<Poetry> GetPoetryByType(CreateEnum kind, int partner, int views,int limit) {
        return this.baseMapper.selectHotLiters(kind.getValue(),partner,views,limit);
    }

    @Override
    public List<Poetry> GetPoetryByDate(CreateEnum kind, Date date, int start, int limit) {
        LambdaQueryWrapper<Poetry> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Poetry::getKind,kind.getValue())
                .lt(BaseEntity::getCreateDat,date)
                .eq(BaseEntity::getIsDel,0)
                .orderByDesc(BaseEntity::getCreateDat)
                .last("limit "+start+","+limit);

        return this.baseMapper.selectList(wrapper);

    }

    @Override
    public List<Poetry> GetInitNewPoetry(Date date, int limit) {
        LambdaQueryWrapper<Poetry> wrapper = new LambdaQueryWrapper<>();
        wrapper.lt(BaseEntity::getCreateDat,date)
                .eq(BaseEntity::getIsDel,0)
                .eq(Poetry::getFinish,0)
                .groupBy(Poetry::getKind)
                .orderByDesc(BaseEntity::getCreateDat)
                .last("limit "+limit);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public List<Poetry> GetInitHotPoetry(int partner, int views, int limit) {
        return this.baseMapper.selectInitHotLiters(partner,views,limit);
    }

    @Override
    public Poetry GetPoetryById(String uid) {
        return this.baseMapper.selectById(uid);
    }

    @Override
    public Poetry GetTopPoetry() {
        LambdaQueryWrapper<Poetry> wrapper = new LambdaQueryWrapper();
        wrapper.eq(Poetry::getFinish,0)
                .eq(Poetry::getIsTop,1)
                .eq(Poetry::getIsDel,0)
                .orderByDesc(Poetry::getCreateDat)
                .last("LIMIT 1");
        return this.baseMapper.selectOne(wrapper);
    }

    @Override
    public List<Poetry> GetRecomPoetry(int startIndex, int pageSize) {
        LambdaQueryWrapper<Poetry> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Poetry::getIsTop,1)
                .eq(Poetry::getIsTop,0)
                .orderByDesc(BaseEntity::getCreateDat)
                .select(Poetry::getUid,Poetry::getName)
                .last("limit "+startIndex+","+pageSize);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public List<Poetry> QueryPoetryByKey(String keyword, int startIndex, int pageSize) {
        Pageable pageable = PageRequest.of(startIndex,pageSize);
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        if(!StringUtil.isNullOrEmpty(keyword)) {
            builder.should(QueryBuilders.matchPhraseQuery("seter", keyword).boost(6.0f));
            builder.should(QueryBuilders.matchPhraseQuery("name", keyword).boost(6.0f));

            builder.should(QueryBuilders.matchPhraseQuery("description",keyword).boost(5.0f));
            builder.should(QueryBuilders.matchPhraseQuery("topic",keyword).boost(3.0f));
            builder.should(QueryBuilders.matchPhraseQuery("purpose",keyword).boost(1.0f));
        }
        //排序条件
        FieldSortBuilder fsb = SortBuilders.fieldSort("createDat").order(SortOrder.DESC);

        //构建查询
        SearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(builder)
                .withSort(fsb)
                .withPageable(pageable)
                .build();
        List<Poetry> list =  poetryESRepository.search(query).getContent();

        return list;
    }
}
