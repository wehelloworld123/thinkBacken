package com.myIsoland.service.product;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myIsoland.dao.PaintPartESRepository;
import com.myIsoland.enitity.product.PaintingPart;
import com.myIsoland.enums.CreateEnum;
import com.myIsoland.mapper.product.PaintingPartMapper;
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

import java.awt.image.ImageConsumer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class PaintingPartServiceImpl extends ServiceImpl<PaintingPartMapper,PaintingPart> implements PaintingPartService {
    @Autowired
    private PaintPartESRepository paintPartESRepository;

    @Override
    public List<PaintingPart> GetPaintingPartByPaintId(String uid) {
        return this.baseMapper.selectPaintingParts(uid);
    }

    @Override
    public int UpdatePart(PaintingPart data) {
        return this.baseMapper.updateById(data);
    }

    @Override
    public PaintingPart GetCharptDetail(Long id) {
        return this.baseMapper.selectPartById(id);
    }



    @Override
    public ResultSet<PaintingPart> GetPartByType(CreateEnum kind, int startIndex, int pageSize) {

        PageHelper.offsetPage(startIndex,pageSize,true);

        List<PaintingPart> list = this.baseMapper.selectPaintingPartByType(kind.getValue());
        ResultSet<PaintingPart> resultSet = new ResultSet<>();
        PageInfo<PaintingPart> pageInfo = new PageInfo<PaintingPart>(list);
        resultSet.setList(list);
        resultSet.setCount(pageInfo.getTotal());
        return resultSet;
    }

    @Override
    public List<PaintingPart> QueryPaintingPartByKey(String keyword, int startIndex, int pageSize) {
        Pageable pageable = PageRequest.of(startIndex,pageSize);
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        if(!StringUtil.isNullOrEmpty(keyword)) {
            builder.should(QueryBuilders.matchPhraseQuery("subject", keyword).boost(6.0f));
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
        List<PaintingPart> list = paintPartESRepository.search(query).getContent();
        return list;
    }

    @Override
    public ResultSet<PaintingPart> GetPartByDateOrNo(Date date, int type, int startIndex, int pageSize) {

        PageHelper.offsetPage(startIndex,pageSize,true);
        List<PaintingPart> list = new ArrayList<>();
        if(type==100) {//最新
            list = this.baseMapper.selectPaintingPartByDate(date);
        }else  if(type==101){//最热
            list = this.baseMapper.selectPaintingPartByHot();
        }else if(type==102){//已完成
            list = this.baseMapper.selectFinPaintingPartByHot();
        }

        ResultSet<PaintingPart> resultSet = new ResultSet<>();
        PageInfo<PaintingPart> pageInfo = new PageInfo<PaintingPart>(list);
        resultSet.setList(list);
        resultSet.setCount(pageInfo.getTotal());
        return resultSet;
    }
}
