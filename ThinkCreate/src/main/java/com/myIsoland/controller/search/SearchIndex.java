package com.myIsoland.controller.search;

import com.myIsoland.common.component.RedisCacheService;
import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.constant.RedisConstant;
import io.swagger.annotations.Api;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@Api(value = "用户搜索界面")
@RequestMapping("Search")
public class SearchIndex {

    @Autowired
    private RedisCacheService redisCacheService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    /**
     * @param keyword   搜索标题
     * @param pageable startIndex = 第几页参数, pageSize = 每页显示条数
     */
    @GetMapping("search")
    public Object Search(String keyword, int startIndex, int pageSize){
        Pageable pageable1 = new PageRequest(0,5);
/*        //按标题进行搜索
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("title", title);

        //如果实体和数据的名称对应就会自动封装，pageable分页参数
        Iterable<Notice> listIt =  nticeRepository.search(queryBuilder,pageable);

        //Iterable转list
        List<Notice> list= Lists.newArrayList(listIt);

        return CommandResult.ofSucceed(list);*/
        return null;
    }

    @GetMapping("keywordRecom")
    public Object KeywordRecom(String keyword){
        String maxKeyword = keyword + "zzzzz";
        Set<String> set = stringRedisTemplate.opsForZSet().rangeByLex(RedisConstant.SEARCH_KEYWORD,
                RedisZSetCommands.Range.range().gte(keyword).lt(maxKeyword), RedisZSetCommands.Limit.limit().count(30));

        return AjaxResult.success(set);
    }

}
