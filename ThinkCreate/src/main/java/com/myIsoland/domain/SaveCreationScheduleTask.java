package com.myIsoland.domain;

import com.myIsoland.common.component.RedisCacheService;
import com.myIsoland.common.component.ThinkRPCException;
import com.myIsoland.enitity.product.LiterContent;
import com.myIsoland.enums.CodeEnum;
import com.myIsoland.service.product.LiterContentService;
import com.myIsoland.service.product.PaintContentService;
import com.myIsoland.service.product.PoemContentService;
import com.myIsoland.service.product.RecommendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class SaveCreationScheduleTask {
    private Logger logger = LoggerFactory.getLogger(SaveCreationScheduleTask.class);

    @Autowired
    private RedisCacheService redis;
    @Autowired
    private LiterContentService literContentService;
    @Autowired
    private PaintContentService paintContentService;
    @Autowired
    private PoemContentService poemContentService;
    @Autowired
    private RecommendService recommendService;
    /**
     * 同步交流贴点赞
     */
    @Scheduled(cron = "0 0/5 * * * ? ") //每5分钟
    public void syncLitetatureLike() {
        logger.info("======================开始 同步文学作品点赞量======================");
        Long startTime = System.nanoTime();
        List<LiterContent> data = new ArrayList<>();
        //从redis取值封装List
        int prefixLength = "c_like_lit_".length();
        Set<String> keySet = redis.getLikeFields("c_like_lit_*");
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
            data.add(new LiterContent(key.substring(prefixLength),likes,favorer+""));
            //更新到数据库中
            Long endTime = System.nanoTime();
            logger.info("本次文学作品同步成功, 总耗时: {}", (endTime - startTime) / 1000000 + "ms");
            logger.info("======================结束 同步交流贴点赞量结束======================");
        }
        try {
            if(data.size()>0) {
                redis.delFields(keySet);
                literContentService.batchUpdateLikes(data);
            }
        }catch (Exception e){
            throw new ThinkRPCException(CodeEnum.SQL_EXCEPTION_CODE.getCode(),CodeEnum.SQL_EXCEPTION_CODE.getMessage(),e);
        }
    }
}
