package com.myIsoland;

import com.myIsoland.common.util.DateUtils;
import com.myIsoland.dao.LiteratureESRepository;
import com.myIsoland.enitity.product.Literature;
import com.myIsoland.enitity.product.PoemContent;
import com.myIsoland.model.ResultSet;
import com.myIsoland.service.product.LiteratureService;
import io.netty.util.internal.StringUtil;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchExtBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest

public class productESTestCase {
    @Autowired
    private LiteratureESRepository literatureESRepository;
    @Autowired
    private LiteratureService literatureService;
    @Test
    public void check(){
        String keyword = "新语";
        Pageable pageable = PageRequest.of(0,4);
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


        System.out.println(list);

    }
}
