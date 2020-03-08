package com.myIsoland.domain;

import com.myIsoland.annotation.DataSource;
import com.myIsoland.common.component.JedisCacheService;
import com.myIsoland.common.component.JedisConfig;
import com.myIsoland.common.component.RedisCacheService;
import com.myIsoland.common.component.ThinkRPCException;
import com.myIsoland.enitity.community.*;
import com.myIsoland.enums.CodeEnum;
import com.myIsoland.enums.UserCorpStsType;
import com.myIsoland.service.community.*;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author xuyong
 * @date 2020/01/24 下午4:55
 */
@Component
public class ScheduledTask_UpdaLikes {

    private Logger logger = LoggerFactory.getLogger(ScheduledTask_UpdaLikes.class);

    @Autowired
    private RedisCacheService redis;
    @Autowired
    private DisscussService disscussService;
    @Autowired
    private UserConcernService userConcernService;
    @Autowired
    private UserCorpService userCorpService;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private CommentService commentService;
    /**
     * 同步交流贴点赞
     */
    @Scheduled(cron = "0 0/5 * * * ? ") //每5分钟
    public void syncDiscussLike() {
        logger.info("======================开始 同步交流贴点赞量======================");
        Long startTime = System.nanoTime();
        List<Disscuss> data = new ArrayList<>();
        //从redis取值封装List
        int prefixLength = "f_like_dis_".length();
        Set<String> keySet = redis.getLikeFields("f_like_dis_*");
        for (String key : keySet) {
            Map<Object,Object> map = redis.getHashMap(key);
            redis.delete(key);
            StringBuilder favorer = new StringBuilder();
            int likes = 0;
            for(Map.Entry<Object, Object> entry:map.entrySet()){//keySet获取map集合key的集合  然后在遍历key即可
                entry.getKey();
                if(entry.getValue().equals("1")){
                    likes +=1;
                    favorer.append((String) entry.getKey()+";");
                }else if (entry.getValue().equals("0")){

                }
                System.out.println("key:"+entry.getKey()+" vlaue:"+entry.getValue());
            }
            data.add(new Disscuss(Long.parseLong(key.substring(prefixLength)),likes,favorer+""));
            //更新到数据库中
            Long endTime = System.nanoTime();
            logger.info("本次文章访问量同步成功, 总耗时: {}", (endTime - startTime) / 1000000 + "ms");
            logger.info("======================结束 同步交流贴点赞量结束======================");
        }
        try {
            if(data.size()>0) {
                redis.delFields(keySet);
                disscussService.batchUpdateLikes(data);
            }
        }catch (Exception e){
            throw new ThinkRPCException(CodeEnum.SQL_EXCEPTION_CODE.getCode(),CodeEnum.SQL_EXCEPTION_CODE.getMessage(),e);
        }
    }

    /**
     * 同步评论点赞
     */
    @Scheduled(cron = "0 0/5 * * * ? ") //每半小时
    public void syncCommentLike() {
        logger.info("======================开始 同步评论点赞量======================");
        Long startTime = System.nanoTime();
        List<Comment> data = new ArrayList<>();
        //从redis取值封装List
        int prefixLength = "f_like_com_".length();
        Set<String> keySet = redis.getLikeFields("f_like_com_*");
        for (String key : keySet) {
            Map<Object,Object> map = redis.getHashMap(key);
            redis.delFields(keySet);
            StringBuilder favorer = new StringBuilder();
            int likes = 0;
            for(Map.Entry<Object, Object> entry:map.entrySet()){//keySet获取map集合key的集合  然后在遍历key即可
                if(entry.getValue().equals("1")){
                    likes +=1;
                    favorer.append((String) entry.getKey()+";");
                    map.replace(entry.getKey(),"0");
                }else if (entry.getValue().equals("0")){

                }
                System.out.println("key:"+entry.getKey()+" vlaue:"+entry.getValue());
            }
            data.add(new Comment(Long.parseLong(key.substring(prefixLength)),likes,favorer+""));
        }
        //更新到数据库中
        Long endTime = System.nanoTime();
        logger.info("本次评论同步成功, 总耗时: {}", (endTime - startTime) / 1000000 + "ms");
        logger.info("======================结束 评论点赞量结束======================");
        try {
            if(data.size()>0) {
                commentService.batchUpdateLikes(data);
            }
        }catch (Exception e){
            throw new ThinkRPCException(CodeEnum.SQL_EXCEPTION_CODE.getCode(),CodeEnum.SQL_EXCEPTION_CODE.getMessage(),e);
        }
    }
/*    *//**
     * 定时删除交流贴点赞内存
     *//*
    @Scheduled(cron = "0 0/31 * * * ? ") //每半小时
    public void synDelDiscussLike() {
        logger.info("======================开始 定时删除交流贴点赞内存======================");
        Long startTime = System.nanoTime();
        Set<String> keys = redis.getLikeFields("f_*");
        redis.delFields(keys);
        Long endTime = System.nanoTime();
        logger.info("本次文章访问量同步成功, 总耗时: {}", (endTime - startTime) / 1000000 + "ms");
    }*/
    /**
     * 同步活动和帖子关注量
     */
    @Scheduled(cron = "0 0/10 * * * ? ") //每十分钟
    public void syncConcern() {
        logger.info("======================开始 同步活动和帖子关注量======================");
        Long startTime = System.nanoTime();
        List<UserConcern> data = new ArrayList<>();
        //从redis取值封装List
        int prefixLength = "f_con_dis_".length();
        int prefixLength2 = "f_con_act_".length();
        Set<String> keySet = redis.getLikeFields("f_con_*");
        for (String key : keySet) {
            Map<Object,Object> map = redis.getHashMap(key);
            for(Map.Entry<Object, Object> entry:map.entrySet()){//keySet获取map集合key的集合  然后在遍历key即可
                entry.getKey();
                if(entry.getValue().equals("0")){//关注
                    data.add(new UserConcern(null,Long.parseLong(key.substring(prefixLength)),entry.getKey()+"",0));
                }else if(entry.getValue().equals("1")){
                    data.add(new UserConcern(null,key.substring(prefixLength2),entry.getKey()+"",0));
                }
                System.out.println("key:"+entry.getKey()+" vlaue:"+entry.getValue());
            }
            //更新到数据库中
            Long endTime = System.nanoTime();
            logger.info("本次文章访问量同步成功, 总耗时: {}", (endTime - startTime) / 1000000 + "ms");
            logger.info("======================结束 同步活动和帖子关注量======================");
        }
        try {
            userConcernService.saveBatch(data);
        }catch (Exception e){
            throw new ThinkRPCException(CodeEnum.SQL_EXCEPTION_CODE.getCode(),CodeEnum.SQL_EXCEPTION_CODE.getMessage(),e);
        }
    }

    /**
     * 同步社团活动浏览量
     */
    @Scheduled(cron = "0 0/30 * * * ? ") //每三十分钟
    public void syncCorporViews() {
        logger.info("======================开始 同步社团活动浏览量======================");
        Long startTime = System.nanoTime();
        List<Activity> data = new ArrayList<>();
        //从redis取值封装List
        Set<ZSetOperations.TypedTuple<Object>> keySet = redis.getZSetRange("act_views",1,1000000);
        Iterator<ZSetOperations.TypedTuple<Object>> iterator = keySet.iterator();
        while (iterator.hasNext()){
            ZSetOperations.TypedTuple<Object> typedTuple = iterator.next();
            data.add(new Activity(typedTuple.getValue().toString(), typedTuple.getScore().intValue()));
        }
            //更新到数据库中
            Long endTime = System.nanoTime();
            logger.info("本次文章访问量同步成功, 总耗时: {}", (endTime - startTime) / 1000000 + "ms");
            logger.info("======================结束 同步社团活动浏览量======================");
        try {
            activityService.updateBatchById(data);
            redis.delete("act_views");
        } catch (Exception e) {
            throw new ThinkRPCException(CodeEnum.SQL_EXCEPTION_CODE.getCode(), CodeEnum.SQL_EXCEPTION_CODE.getMessage(),e);
        }
    }
        /**
         * 同步用户社团申请量
         */
        @Scheduled(cron = "0 0/20 * * * ? ") //每二十分钟
        public void syncCorporation() {
            logger.info("======================开始 同步社团申请用户======================");
            Long startTime = System.nanoTime();
            List<UserCorportion> data = new ArrayList<>();
            //从redis取值封装List
            int prefixLength = "f_corp_apply_".length();
            Set<String> keySet = redis.getLikeFields("f_corp_apply_*");
            for (String key : keySet) {
                String subStr = key.substring(prefixLength);
                int index = subStr.indexOf("-");
                data.add(new UserCorportion(subStr.substring(0,index),subStr.substring(index+1), UserCorpStsType.UNAUDITED));
            }
            try {
                userCorpService.saveBatch(data);
            }catch (Exception e){
                throw new ThinkRPCException(CodeEnum.SQL_EXCEPTION_CODE.getCode(),CodeEnum.SQL_EXCEPTION_CODE.getMessage(),e);
            }
            //更新到数据库中
            Long endTime = System.nanoTime();
            logger.info("本次文章访问量同步成功, 总耗时: {}", (endTime - startTime) / 1000000 + "ms");
            logger.info("======================结束 同步社团申请用户======================");
    }
}
