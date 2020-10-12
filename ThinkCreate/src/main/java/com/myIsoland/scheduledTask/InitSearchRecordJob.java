package com.myIsoland.scheduledTask;

import com.myIsoland.common.component.RedisCacheService;
import com.myIsoland.common.util.DateUtils;
import com.myIsoland.constant.RedisConstant;
import com.myIsoland.dao.LiteratureESRepository;
import com.myIsoland.enitity.search.SearchRecord;
import com.myIsoland.service.search.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.*;

public class InitSearchRecordJob {
    private Logger logger = LoggerFactory.getLogger(InitSearchRecordJob.class);

    @Autowired
    private RedisCacheService redisCacheService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private SearchService searchService;

    @Autowired
    private LiteratureESRepository literatureESRepository;

    /**
     * 同步每天的搜索记录到数据库
     */
    @Scheduled(cron = "0 0 1 * * ?") //每天凌晨1点执行一次
    public void syncUserSearchRecordToDb() {
        logger.info("======================syncUserSearchRecordToDb start======================");
        Long startTime = System.nanoTime();
        List<SearchRecord> data = new ArrayList<>();


        /**
         * Step 1 获取当前时间和截至时间
         */
        Date startDate = DateUtils.getNowDate();//当前时间
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) - 1);
        Date endDate =  c.getTime();

        int startIndex = 0;
        int pageSize = 200;

        List<SearchRecord> list = new ArrayList<>();

        Set<ZSetOperations.TypedTuple<Object>>  typedTuples = redisCacheService.getZSetRange(RedisConstant.SEARCH_KEYWORD,new Long(1),new Long(1000));
        if(typedTuples.size()>0) {
            list = searchService.selectAllKeysByDateOrCount(startDate, endDate, new Long(10), startIndex, pageSize);
            Iterator<ZSetOperations.TypedTuple<Object>> iterator = typedTuples.iterator();
            while (iterator.hasNext()) {
                SearchRecord record = new SearchRecord();
                ZSetOperations.TypedTuple<Object> typedTuple = iterator.next();
                record.setKey(typedTuple.getValue().toString());
                record.setCount(new Double(typedTuple.getScore()).longValue());
                list.add(record);


                while(list.size() == pageSize){
                    searchService.saveOrUpdateBatch(list);
                    list.clear();

                }
            }
        }
        if (list.size() >0){
            searchService.saveOrUpdateBatch(list);
            list.clear();
        }
        redisCacheService.delete(RedisConstant.SEARCH_KEYWORD);
        logger.info("======================syncUserSearchRecordToDb end======================");

    }
    /**
     * 同步交流贴点赞
     */
    @Scheduled(cron = "0 0 3 * * ?") //每天凌晨3点执行一次
    public void syncUserSearchRecord() {
        logger.info("======================syncUserSearchRecord start======================");
        Long startTime = System.nanoTime();
        List<SearchRecord> data = new ArrayList<>();


        /**
         * Step 1 获取当前时间和截至时间
         */
        Date startDate = DateUtils.getNowDate();//当前时间
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) - 1);
        Date endDate =  c.getTime();

        int startIndex = 0;
        int pageSize = 200;

        List<SearchRecord> list = new ArrayList<>();

        Set<ZSetOperations.TypedTuple<String>> typedTuples = new HashSet<>();
        do{
            if(list.size()>0){
                list.clear();
            }
            if(typedTuples.size()>0){
                typedTuples.clear();
            }
            list = searchService.selectAllKeysByDateOrCount(startDate,endDate,new Long(10),startIndex,pageSize);
            if(!list.isEmpty()){
                for(SearchRecord searchRecord : list){
                    ZSetOperations.TypedTuple<String> typedTuple = new DefaultTypedTuple<>(searchRecord.getKey(),new Double(0));
                    typedTuples.add(typedTuple);
                }
                stringRedisTemplate.opsForZSet().add(RedisConstant.SEARCH_KEYWORD,typedTuples);
            }

        }while (list.size()>=pageSize);
        logger.info("======================syncUserSearchRecord end======================");

    }
}
