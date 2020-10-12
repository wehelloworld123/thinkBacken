package com.myIsoland.service.product;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myIsoland.common.base.BaseEntity;
import com.myIsoland.dao.PoetrySetESRepository;
import com.myIsoland.enitity.product.PoemContent;
import com.myIsoland.enitity.product.PoemSet;
import com.myIsoland.enitity.product.Poetry;
import com.myIsoland.mapper.product.PoemSetMapper;
import com.myIsoland.model.ResultSet;
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

import java.util.List;
import java.util.Map;

@Service
public class PoemSetServiceImpl  extends ServiceImpl<PoemSetMapper,PoemSet> implements PoemSetService {

    @Autowired
    private PoetrySetESRepository poetrySetESRepository;
    @Override
    public List<PoemSet> GetPoemSetByPoetryId(String uid,int start,int limit) {
        return this.baseMapper.selectPoemSets(uid);
    }



    @Override
    public List<PoemSet> GetPoemSetsByPoetryId(String uid,int start,int limit,Boolean containRoot) {
        PageHelper.offsetPage(start,limit,true);
        QueryWrapper<PoemSet> wrapper = new QueryWrapper<>();
        wrapper.select("id","charpter","description","is_lock","finish")
                .lambda()
                .eq(PoemSet::getPoetryId,uid)
                .eq(PoemSet::getRoot,containRoot?1:0)
                .orderByAsc(PoemSet::getOrd);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public int UpdatePoemSet(PoemSet data) {
        return this.baseMapper.updateById(data);
    }

    @Override
    public PoemSet GetPoemSetDetail(Long id) {
        return this.baseMapper.selectChaptById(id);
    }

    @Override
    public ResultSet<PoemSet> GetPoemSetByHotNo(int startIndex,int pageSize) {
        PageHelper.offsetPage(startIndex,pageSize,true);
        PageHelper.offsetPage(startIndex,pageSize,true);

        List<PoemSet> list = this.baseMapper.selectPoemSetByHotNo();
        ResultSet<PoemSet> resultSet = new ResultSet<>();
        PageInfo<PoemSet> pageInfo = new PageInfo<>(list);
        resultSet.setList(list);
        resultSet.setCount(pageInfo.getTotal());
        return resultSet;
    }

    @Override
    public List<PoemSet> QueryPoetrySetByKey(String keyword, int startIndex, int pageSize) {
        Pageable pageable = PageRequest.of(startIndex,pageSize);
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        if(!StringUtil.isNullOrEmpty(keyword)) {
            builder.should(QueryBuilders.matchPhraseQuery("charpter", keyword).boost(6.0f));
            builder.should(QueryBuilders.matchPhraseQuery("introduce",keyword).boost(5.0f));
        }
        //排序条件
        FieldSortBuilder fsb = SortBuilders.fieldSort("createDat").order(SortOrder.DESC);

        //构建查询
        SearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(builder)
                .withSort(fsb)
                .withPageable(pageable)
                .build();
        List<PoemSet> list =  poetrySetESRepository.search(query).getContent();

        return list;
    }

    @Override
    public Map<String,Object> GetPoetryBySetId(Long id) {
        return this.baseMapper.selectPoetryBySetId(id);
    }
}
