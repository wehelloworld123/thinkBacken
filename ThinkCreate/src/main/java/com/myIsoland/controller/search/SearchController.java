package com.myIsoland.controller.search;

import com.myIsoland.common.component.RedisCacheService;
import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.common.util.DateUtils;
import com.myIsoland.common.util.StringUtils;
import com.myIsoland.constant.RedisConstant;
import com.myIsoland.dao.LiterChapterESRepository;
import com.myIsoland.dao.PaintPartESRepository;
import com.myIsoland.dao.PoetrySetESRepository;
import com.myIsoland.enitity.product.LiterCharpt;
import com.myIsoland.enitity.product.Literature;
import com.myIsoland.enitity.product.PaintingPart;
import com.myIsoland.enitity.product.PoemSet;
import com.myIsoland.enitity.search.SearchRecord;
import com.myIsoland.enums.CodeEnum;
import com.myIsoland.service.search.SearchService;
import io.swagger.annotations.Api;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Pageable;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
@Api(value = "搜索界面")
@RequestMapping("Search")
public class SearchController {
    @Autowired
    private LiterChapterESRepository literChapterESRepository;
    @Autowired
    private PaintPartESRepository paintPartESRepository;
    @Autowired
    private PoetrySetESRepository poetrySetESRepository;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private SearchService searchService;
    @GetMapping("keywordRecom")
    public Object KeywordRecom(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();

//过去七天
        c.setTime(new Date());
        c.add(Calendar.DATE, - 7);
        Date d = c.getTime();
        String day = format.format(d);
        System.out.println("过去七天："+day);

//过去一月
        c.setTime(new Date());
        c.add(Calendar.MONTH, -1);
        Date m = c.getTime();
        String mon = format.format(m);
        System.out.println("过去一个月："+mon);

/*//过去三个月
        c.setTime(new Date());
        c.add(Calendar.MONTH, -3);
        Date m3 = c.getTime();
        String mon3 = format.format(m3);
        System.out.println("过去三个月："+mon3);

//过去一年
        c.setTime(new Date());
        c.add(Calendar.YEAR, -1);
        Date y = c.getTime();
        String year = format.format(y);
        System.out.println("过去一年："+year);*/
         List<SearchRecord> list = searchService.selectAllKeysByDateOrCount(m,DateUtils.getNowDate(),
                 new Long(10),0,10);
        return AjaxResult.success(list);
    }
    @GetMapping("/searchChapter")
    public Object SearchChapter(int type,String keyword,int startIndex,int pageSize){
        if(StringUtils.isEmpty(keyword)){
            return AjaxResult.error(CodeEnum.INCORRECT_REQ_PARAM.getCode(),CodeEnum.INCORRECT_REQ_PARAM.getMessage(),null);
        }else{
            redisTemplate.opsForZSet().incrementScore(RedisConstant.SEARCH_KEYWORD,keyword,1);
        }
        Pageable pageable = PageRequest.of(startIndex, pageSize);
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        if(type==0) {
            if (!StringUtils.isEmpty(keyword)) {
                builder.should(QueryBuilders.matchPhraseQuery("title", keyword).boost(6.0f));
                builder.should(QueryBuilders.matchPhraseQuery("introduce", keyword).boost(5.0f));
                builder.should(QueryBuilders.matchPhraseQuery("label", keyword).boost(3.0f));
            }
            //排序条件
            FieldSortBuilder fsb = SortBuilders.fieldSort("createDat").order(SortOrder.DESC);

            //构建查询
            SearchQuery query = new NativeSearchQueryBuilder()
                    .withQuery(builder)
                    .withSort(fsb)
                    .withPageable(pageable)
                    .build();
            List<LiterCharpt> list = literChapterESRepository.search(query).getContent();
            return AjaxResult.success(list);
        }else if(type==1){
            if (!StringUtils.isEmpty(keyword)) {
                builder.should(QueryBuilders.matchPhraseQuery("subject", keyword).boost(6.0f));
                builder.should(QueryBuilders.matchPhraseQuery("introduce", keyword).boost(5.0f));
              //  builder.should(QueryBuilders.matchPhraseQuery("label", keyword).boost(3.0f));
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
            return AjaxResult.success(list);
        }else if(type==2){
            if (!StringUtils.isEmpty(keyword)) {
                builder.should(QueryBuilders.matchPhraseQuery("charpter", keyword).boost(6.0f));
                builder.should(QueryBuilders.matchPhraseQuery("description", keyword).boost(5.0f));
                //  builder.should(QueryBuilders.matchPhraseQuery("label", keyword).boost(3.0f));
            }
            //排序条件
            FieldSortBuilder fsb = SortBuilders.fieldSort("createDat").order(SortOrder.DESC);

            //构建查询
            SearchQuery query = new NativeSearchQueryBuilder()
                    .withQuery(builder)
                    .withSort(fsb)
                    .withPageable(pageable)
                    .build();
            List<PoemSet> list = poetrySetESRepository.search(query).getContent();
            return AjaxResult.success(list);
        }else{
            return AjaxResult.error(CodeEnum.INCORRECT_REQ_PARAM.getCode(),CodeEnum.INCORRECT_REQ_PARAM.getMessage(),null);
        }
    }
}
