package com.myIsoland.service.product;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.myIsoland.common.base.BaseEntity;
import com.myIsoland.common.base.PageInfo;
import com.myIsoland.common.util.CaculateUtils;
import com.myIsoland.constant.ProjectConstant;
import com.myIsoland.dao.LiterChapterESRepository;
import com.myIsoland.enitity.product.LiterCharpt;
import com.myIsoland.enums.CreateKind;
import com.myIsoland.mapper.product.LiterCharptMapper;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class LiterCharpServiceImpl extends ServiceImpl<LiterCharptMapper,LiterCharpt> implements LiterCharpService {

    @Autowired
    private LiterChapterESRepository literChapterESRepository;

    @Override
    public List<LiterCharpt> GetBookCharpt(String bookId) {
        return this.baseMapper.selectBookCharps(bookId);
    }
    @Override
    public int UpdateCharpt(LiterCharpt data) {
        return this.baseMapper.updateById(data);
    }

    @Override
    public LiterCharpt GetCharptDetail(Long id) {
        return this.baseMapper.selectChaptById(id);
    }

    @Override
    public ResultSet<LiterCharpt> GetChapterByType(CreateKind kind,int startIndex,int pageSize) {

        PageHelper.offsetPage(startIndex,pageSize,true);

        List<LiterCharpt> list = this.baseMapper.selectChapterByType(kind.getValue());
        ResultSet<LiterCharpt> resultSet = new ResultSet<>();
        PageInfo<LiterCharpt> pageInfo = new PageInfo<LiterCharpt>(list);
        resultSet.setList(list);
        resultSet.setCount(pageInfo.getTotal());
        return resultSet;
    }

    @Override
    public List<LiterCharpt> QueryLiterChaptByKey(String keyword, int startIndex, int pageSize) {
        Pageable pageable = PageRequest.of(startIndex,pageSize);
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        if(!StringUtil.isNullOrEmpty(keyword)) {
            builder.should(QueryBuilders.matchPhraseQuery("title", keyword).boost(6.0f));
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
        List<LiterCharpt> list =  literChapterESRepository.search(query).getContent();

        return list;
    }

    @Override
    public ResultSet<LiterCharpt> GetChapterByDateOrNo(Date date, int type, int startIndex, int pageSize) {

        PageHelper.offsetPage(startIndex,pageSize,true);
        List<LiterCharpt> list = new ArrayList<>();
        if(type==100) {//最新
            list = this.baseMapper.selectChapterByDate(date);
        }else  if(type==101){//最热
            list = this.baseMapper.selectChapterByHot();
        }else if(type==102){//已完成
            list = this.baseMapper.selectFinChapterByHot();
        }

        ResultSet<LiterCharpt> resultSet = new ResultSet<>();
        PageInfo<LiterCharpt> pageInfo = new PageInfo<LiterCharpt>(list);
        resultSet.setList(list);
        resultSet.setCount(pageInfo.getTotal());
        return resultSet;
    }
}
