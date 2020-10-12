package com.myIsoland.service.product;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.myIsoland.common.base.BaseEntity;
import com.myIsoland.common.base.PageInfo;
import com.myIsoland.dao.PaintingESRepository;
import com.myIsoland.enitity.product.Painting;
import com.myIsoland.enums.CreateEnum;
import com.myIsoland.enums.CreateKind;
import com.myIsoland.mapper.product.PaintingMapper;
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

import java.awt.*;
import java.util.Date;
import java.util.List;
import java.util.Queue;

@Service
public class PaintingServiceImpl extends ServiceImpl<PaintingMapper,Painting> implements PaintingService{
   @Autowired
   private PaintingESRepository paintingESRepository;

    @Override
    public int SavePainting(Painting data) {
        return this.baseMapper.insert(data);
    }

    @Override
    public int UpdatePainting(Painting data) {
        return this.baseMapper.updateById(data);
    }

    @Override
    public ResultSet<Painting> GetPaintingByType(CreateEnum kind, int partner, int views, int start,int limit) {
        ResultSet<Painting> resultSet = new ResultSet<>();
        resultSet.setList(this.baseMapper.selectPaintByType(kind.getValue(),partner,views,start,limit));
        return resultSet;
    }

    @Override
    public ResultSet<Painting> GetPaintingByDate(CreateEnum kind, Date date, int start, int limit) {
        PageHelper.offsetPage(start,limit,false);
        LambdaQueryWrapper<Painting> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Painting::getKind,kind)
                .le(BaseEntity::getCreateDat,date)
                .eq(Painting::getFinish,0)
                .eq(BaseEntity::getIsDel,0)
                .orderByDesc(BaseEntity::getCreateDat)
                .select(Painting::getUid,Painting::getName,Painting::getCover,Painting::getKind,Painting::getDescription,Painting::getCopyright,Painting::getPurpose,Painting::getCopyright
                        ,Painting::getForm,Painting::getSeter,Painting::getSection,Painting::getTopic);
        ResultSet<Painting> resultSet = new ResultSet<>();
        List<Painting> list = this.baseMapper.selectList(wrapper);
        PageInfo<Painting> pageInfo = new PageInfo<Painting>(list);
        resultSet.setList(list);
        return resultSet;
    }

    @Override
    public Painting GetPaintingById(String uid) {
        LambdaQueryWrapper<Painting> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Painting::getUid,uid)
                .eq(Painting::getIsDel,0);
        return this.baseMapper.selectOne(wrapper);
    }

    @Override
    public Painting GetTopPainting() {
        LambdaQueryWrapper<Painting> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Painting::getIsTop,1)
                .eq(Painting::getFinish,0)
                .eq(Painting::getIsDel,0)
                .orderByDesc(Painting::getCreateDat)
                .last("limit 1");
        return this.baseMapper.selectOne(wrapper);
    }

    @Override
    public List<Painting> GetSysRecomPainting(Date startDate,Date endDate,int finish, int startIndex, int pageSize) {
        PageHelper.offsetPage(startIndex,pageSize,false);
        LambdaQueryWrapper<Painting> wrapper = new LambdaQueryWrapper<>();
        wrapper .ge(BaseEntity::getCreateDat,startDate)
                .le(BaseEntity::getCreateDat,endDate)
                .eq(Painting::getFinish,finish)
                .eq(Painting::getIsTop,1)
                .eq(BaseEntity::getIsDel,0)
                .orderByDesc(BaseEntity::getCreateDat)
                .select(Painting::getUid,Painting::getName,Painting::getCover,Painting::getKind,Painting::getDescription,Painting::getCopyright,Painting::getPurpose,Painting::getCopyright
                        ,Painting::getForm,Painting::getSeter,Painting::getSection,Painting::getTopic);
        List<Painting> list = this.baseMapper.selectList(wrapper);
        return list;
    }

    @Override
    public List<Painting> QueryPaintingByKey(String keyword, int startIndex, int pageSize) {
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
        List<Painting> list =  paintingESRepository.search(query).getContent();

        return list;
    }
}
