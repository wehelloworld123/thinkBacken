package com.myIsoland;

import com.myIsoland.common.component.RedisCacheService;
import com.myIsoland.constant.RedisConstant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTestCase {
    @Autowired
    private RedisCacheService redisCacheService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Test
    public void contextLoads() {

        Set<ZSetOperations.TypedTuple<String>> typedTuples = new HashSet<>();
        String word = "abcdefghijklmnopqrstuvwxyz";

        for(int i=0;i<26;i++){
           //String w = word.substring(i,i+1);
            String keyword = null;
           if(i<10){
               keyword = "徐勇"+i;
            }else if(i<20){
                keyword = "张三"+i;
            }else if(i<30){
                keyword = "李四"+i;
            }
            ZSetOperations.TypedTuple<String> typedTuple = new DefaultTypedTuple<>(keyword,new Double(0));
            typedTuples.add(typedTuple);
        }
        stringRedisTemplate.opsForZSet().add("keysad",typedTuples);
    }

    @Test
    public void test() {

       RedisZSetCommands.Range range = new RedisZSetCommands.Range();
        range.gt("徐勇0");
        range.lte("徐勇zzzzzz");
        Set<String>  set = stringRedisTemplate.opsForZSet().rangeByLex("keysad",range);
        System.out.println(set);
    }
}
