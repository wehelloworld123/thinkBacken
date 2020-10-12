package com.myIsoland.service.product;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myIsoland.common.base.BaseEntity;
import com.myIsoland.dao.LiteratureESRepository;
import com.myIsoland.enitity.product.Literature;
import com.myIsoland.enums.CreateKind;
import com.myIsoland.mapper.product.LiteratureMapper;
import com.myIsoland.model.ResultSet;

import io.netty.util.internal.StringUtil;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class LiteratureServiceImpl extends ServiceImpl<LiteratureMapper,Literature> implements LiteratureService {
    @Autowired
    private LiteratureESRepository literatureESRepository;

    @Override
    public int SaveLiterature(Literature data) {

        return this.baseMapper.insert(data);
    }

    @Override
    public int UpdateLiterature(Literature data) {
        return this.baseMapper.updateById(data);
    }

    @Override
    public List<Literature> GetLiteratureByType(CreateKind kind,int partner,int views,int limit) {

        return this.baseMapper.selectHotLiters(kind.getValue(),partner,views,limit);
    }

    @Override
    public List<Literature> GetInitLiterByType(CreateKind kind, int partner, int views,int limit) {
        return this.baseMapper.selectHotLiters(kind.getValue(),partner,views,limit);
    }

    @Override
    public Literature GetLiteratureById(String uid) {
        return this.baseMapper.selectById(uid);
    }

    @Override
    public List<Map<String,Object>> GetAdvanceLiterature() {
        LambdaQueryWrapper<Literature> wrapper = new LambdaQueryWrapper();
        wrapper.eq(Literature::getFinish,-1)
                .eq(Literature::getIsTop,1)
                .eq(Literature::getIsDel,0)
                .orderByDesc(Literature::getCreateDat)
                .last("LIMIT 7")
                .select(Literature::getUid,Literature::getCover);
        return this.baseMapper.selectMaps(wrapper);
    }

    @Override
    public List<Literature> GetHotIpLiteratures(int partner,int views) {
        LambdaQueryWrapper<Literature> wrapper = new LambdaQueryWrapper();
        wrapper
                .ge(Literature::getPartner,partner)
                .ge(Literature::getViews,views)
                .eq(Literature::getFinish,0)
                .eq(Literature::getIsDel,0)
                .orderByDesc(Literature::getCreateDat)
                .select(Literature::getUid,Literature::getName,Literature::getCover,Literature::getCreateDat);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public List<Literature> GetLiteratures(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        LambdaQueryWrapper<Literature> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Literature::getFinish,0)
                .eq(BaseEntity::getIsDel,0)
                .orderByDesc(BaseEntity::getCreateDat);
        return this.baseMapper.selectList(wrapper);

    }

    @Override
    public Literature GetSysRecomOnThinPro(Date date) {
        LambdaQueryWrapper<Literature> wrapper = new LambdaQueryWrapper<>();
        wrapper.ge(BaseEntity::getCreateDat,date)
                .orderByAsc(BaseEntity::getCreateDat)
                .eq(BaseEntity::getIsDel,0)
                .eq(Literature::getFinish,-1)//构思
                .select(Literature::getUid,Literature::getLabel,Literature::getTyp,Literature::getName,Literature::getCover,Literature::getIntroduce)
                .last("limit 1");
        return this.baseMapper.selectOne(wrapper);
    }

    @Override
    public List<Literature> GetSysRecomIpPro(Date date, int startIndex, int pageSize) {
        LambdaQueryWrapper<Literature> wrapper = new LambdaQueryWrapper<>();
        wrapper.ge(BaseEntity::getCreateDat,date)
                .orderByDesc(Literature::getViews)
                .eq(BaseEntity::getIsDel,0)
                .eq(Literature::getFinish,0)//构思
                .select(Literature::getUid,Literature::getLabel,Literature::getTyp,Literature::getName,Literature::getCover,Literature::getIntroduce)
                .last("limit "+startIndex+","+pageSize);
        return this.baseMapper.selectList(wrapper);
    }


   @Override
    public List<Literature> QueryLiteratureByKey(String keyword, int startIndex, int pageSize) {

       Pageable pageable = PageRequest.of(startIndex,pageSize);
       BoolQueryBuilder builder = QueryBuilders.boolQuery();
       if(!StringUtil.isNullOrEmpty(keyword)) {
           builder.should(QueryBuilders.matchPhraseQuery("name", keyword).boost(6.0f));
           builder.should(QueryBuilders.matchPhraseQuery("description",keyword).boost(5.0f));
           builder.should(QueryBuilders.matchPhraseQuery("label",keyword).boost(3.0f));
           builder.should(QueryBuilders.matchPhraseQuery("typ",keyword).boost(1.0f));
       }
       //排序条件
       FieldSortBuilder fsb = SortBuilders.fieldSort("createDat").order(SortOrder.DESC);

       //构建查询
       SearchQuery query = new NativeSearchQueryBuilder()
               .withQuery(builder)
               .withSort(fsb)
               .withPageable(pageable)
               .build();
       List<Literature> list =  literatureESRepository.search(query).getContent();

        return list;
    }

    @Override
    public ResultSet<Map<String, Object>> GetSubjectBookList(Date date,String source,int startIndex,int pageSize) {
        PageHelper.offsetPage(startIndex,pageSize,true);

        List<Map<String,Object>> list = this.baseMapper.selectBookListBySubjectSource(date,source);
        ResultSet<Map<String, Object>> resultSet = new ResultSet<>();
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
        resultSet.setList(list);
        resultSet.setCount(pageInfo.getTotal());
        return resultSet;
    }


}
