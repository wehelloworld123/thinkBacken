package com.myIsoland;

import com.SpringbootStart;
import com.myIsoland.common.component.RedisCacheService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootStart.class)
public class RedisServiceTestCase {

    @Autowired
    private RedisCacheService redisCacheService;

    @Test
    public void testSend(){
        redisCacheService.delete("mykyes");
        Map<String,Object> map = new HashMap<>();
        map.put("1",1);
        map.put("2",2);
        map.put("3",3);
        map.put("4",4);
        map.put("5",5);
        redisCacheService.setHashMap("mykyes",map);
        System.out.println("获取："+redisCacheService.getHashMap("mykyes"));
        System.out.println("删除1："+redisCacheService.delHashField("mykyes","1","2"));
        System.out.println("删除2："+redisCacheService.delHashField("mykyes","2"));
        Map<String,Object> map2 = new HashMap<>();
        map2.put("5",4);
        map2.put("6",6);
        redisCacheService.setHashMap("mykyes",map2);
        System.out.println("获取："+redisCacheService.getHashMap("mykyes"));
    }
    @Test
    public void testScan() throws IOException {
        redisCacheService.delete("my_kyes");
        redisCacheService.delete("my_kyes2");
        Map<String,Object> map = new HashMap<>();
        map.put("1",1);
        map.put("2",2);
        map.put("3",3);
        map.put("4",4);
        map.put("5",5);
        redisCacheService.setHashMap("my_kyes",map);
        Map<String,Object> map2 = new HashMap<>();
        map2.put("1",1);
        map2.put("2",2);
        map2.put("3",3);
        map2.put("4",4);
        redisCacheService.setHashMap("my_kyes2",map2);
        System.out.println("获取："+redisCacheService.getHashMap("my_kyes"));
        System.out.println("获取："+redisCacheService.getHashMap("my_kyes2"));
        Cursor<Map.Entry<Object,Object>> cursor1 = redisCacheService.getAllScanMap("my_kyes");
        while (cursor1.hasNext()) {
            Map.Entry<Object, Object> entry = cursor1.next();
            System.out.println("通过scan(H key, ScanOptions options)方法获取匹配键值对:" + entry.getKey() + "---->" + entry.getValue());
        }
        //关闭cursor
        cursor1.close();
        System.out.println("获取：-------------------------------------------------");
        Cursor<Map.Entry<Object,Object>> cursor = redisCacheService.getAllScanMap("my_*");
        while (cursor.hasNext()) {
            Map.Entry<Object, Object> entry = cursor.next();
            System.out.println("通过scan(H key, ScanOptions options)方法获取匹配键值对:" + entry.getKey() + "---->" + entry.getValue());
        }
        //关闭cursor
        cursor.close();
    }
    @Test
    public void testSet(){
        Map<String,Object> map = new HashMap<>();
        map.put("1",1);
        map.put("2",2);
        map.put("3",3);
        map.put("4",4);
        map.put("5",5);
        System.out.println("新增："+ redisCacheService.zsetByScore("zset_","1",1));
        System.out.println("新增："+ redisCacheService.zsetByScore("zset_","2",-1));
        System.out.println("新增："+ redisCacheService.zsetByScore("zset_","3",1000));
        System.out.println("新增："+ redisCacheService.zsetByScore("zset_","4",100));
        System.out.println("新增："+ redisCacheService.zsetByScore("zset_","5",10));
        Set<ZSetOperations.TypedTuple<Object>> data = redisCacheService.getZSetRange("zset_",-100,10000);
        Iterator<ZSetOperations.TypedTuple<Object>> iterator = data.iterator();
        while (iterator.hasNext()){
            ZSetOperations.TypedTuple<Object> typedTuple = iterator.next();
            Object value = typedTuple.getValue();
            double score = typedTuple.getScore();
            System.out.println("通过rangeWithScores(K key, long start, long end)方法获取RedisZSetCommands.Tuples的区间值:" + value + "---->" + score );
        }

    }
}
