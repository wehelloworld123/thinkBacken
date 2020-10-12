package com.myIsoland.common.component;

import com.myIsoland.enums.CodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisCacheService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;



    /**
     * 读取Hash单个field的值
     * @param key,field
     * @return
     * @author xy
     * @date 22020年1月14日
     */
    public Long setIncre (String key,Long delta) {
        if(delta<0){
            throw new RuntimeException("递增因子必须大于0");
        }
        try {
            return redisTemplate.opsForValue().increment(key,delta);
        } catch (Exception e) {
            throw new ThinkRPCException(CodeEnum.REDIS_EXCEPTION);
        }
    }
    /**
     * 有序表范围有数限制查询
     * @param key
     * @param range
     * @param limit
     * @return Set<Object>
     * @author xy
     * @date 22020年1月14日
     */
    public Set<Object> zsetRangeByLex (String key, RedisZSetCommands.Range range, RedisZSetCommands.Limit limit) {
        try {
            return redisTemplate.opsForZSet().rangeByLex(key,range,limit);
        } catch (Exception e) {

            throw e;
        }
    }
    /**
     * 有序表范围有数限制查询
     * @param key
     * @param range
     * @return Set<Object>
     * @author xy
     * @date 22020年1月14日
     */
    public Set<Object> zsetRangeByLex (String key, RedisZSetCommands.Range range) {
        try {
            return redisTemplate.opsForZSet().rangeByLex(key,range);
        } catch (Exception e) {
            throw new ThinkRPCException(CodeEnum.REDIS_EXCEPTION);
        }
    }
    /**
     * 有序表单个成员添加
     * @param key,field
     * @return Boolean
     * @author xy
     * @date 22020年1月14日
     */
    public Boolean addSingleZset (String key,String value,double v) {
        try {
            return redisTemplate.opsForZSet().add(key,value,v);
        } catch (Exception e) {
            throw new ThinkRPCException(CodeEnum.REDIS_EXCEPTION);
        }
    }

    /**
     * 有序表列表成员添加
     * @param key
     * @param tuples
     * @return Boolean
     * @author xy
     * @date 22020年1月14日
     */
    public Long addBatchZset (String key,Set<ZSetOperations.TypedTuple<Object>> tuples) {
        try {
            return redisTemplate.opsForZSet().add(key,tuples);
        } catch (Exception e) {
            throw new ThinkRPCException(CodeEnum.REDIS_EXCEPTION);
        }
    }
    /**
     * 有序表成员自增
     * @param key,field
     * @return
     * @author xy
     * @date 22020年1月14日
     */
    public Double zsetByScore (String key,String value,double v) {
        try {
            return redisTemplate.opsForZSet().incrementScore(key,value,v);
        } catch (Exception e) {
            throw new ThinkRPCException(CodeEnum.REDIS_EXCEPTION);
        }
    }
    /**
     * 有序表成员自增
     * @param key,field
     * @return
     * @author xy
     * @date 22020年1月14日
     */
    public Set<ZSetOperations.TypedTuple<Object>> getZSetRange (String key, long min, long max) {
        try {
            return redisTemplate.opsForZSet().rangeWithScores(key,min,max);
        } catch (Exception e) {
            throw new ThinkRPCException(CodeEnum.REDIS_EXCEPTION);
        }
    }

    /** 
          * 普通缓存获取 
          * @param key 键 
          * @return 值 
          */
    public Object get(String key){
        return key==null?null:redisTemplate.opsForValue().get(key);
    }
    /** 
          * 普通缓存放入 
          * @param key 键 
          * @param value 值 
          * @return true成功 false失败 
          */
    public boolean setIfAbsent(String key, Object value, long time, TimeUnit unit){
        try{
            return redisTemplate.opsForValue().setIfAbsent(key,value,time,unit);
        }catch(Exception e){
            throw new ThinkRPCException(CodeEnum.REDIS_EXCEPTION);
        }
    }
    /** 
          * 普通缓存放入 
          * @param key 键 
          * @param value 值 
          * @return true成功 false失败 
          */
    public void set(String key, Object value, long time, TimeUnit unit){
        try{
            redisTemplate.opsForValue().set(key,value,time,unit);
        }catch(Exception e){
            throw new ThinkRPCException(CodeEnum.REDIS_EXCEPTION);
        }
    }
    /**
     * 读取Hash单个field的值
     * @param key,field
     * @return
     * @author xy
     * @date 22020年1月14日
     */
    public Object getHashField (String key,String field) {

        try {
            return redisTemplate.opsForHash().get(key,field);
        } catch (Exception e) {
            throw new ThinkRPCException(CodeEnum.REDIS_EXCEPTION);
        }
    }
    /**
     * 直接修改Hash已存在的值 如果field已存在则执行修改,并返回0
     * @param key,field,value
     * @return
     * @author xy
     * @date 22020年1月14日
     */
    public void modifyHashValue (String key,String field,String value) {
        try {
            redisTemplate.opsForHash().put(key,field,value);
        } catch (Exception e) {
            throw new ThinkRPCException(CodeEnum.REDIS_EXCEPTION);
        }
    }
    /**
     * 批量设置Hash的Map集合
     * @param key
     * @return
     * @author xy
     * @date 2020年1月14日
     */
    public void setHashMap(String key, Map map) {
        try {
            redisTemplate.opsForHash().putAll(key,map);
        } catch (Exception e) {
            throw new ThinkRPCException(CodeEnum.REDIS_EXCEPTION);
        }
    }
    /**
     * 读取Hash所有field
     * @param key
     * @return
     * @author xy
     * @date 2020年1月14日
     */
    public Set<Object> getHashFields(String key) {
        try {
            return redisTemplate.opsForHash().keys(key);
        } catch (Exception e) {
            throw new ThinkRPCException(CodeEnum.REDIS_EXCEPTION);
        }
    }

    /**
     * 模糊查询field
     * @param key
     * @return
     * @author xy
     * @date 2020年1月14日
     */
    public Set<String> getLikeFields(String key) {
        try {
            return redisTemplate.keys(key);
        } catch (Exception e) {
            throw new ThinkRPCException(CodeEnum.REDIS_EXCEPTION);
        }
    }
    /**
     * hash模糊匹配查询获的所有map
     * @param key
     * @return
     * @author xy
     * @date 2020年1月14日
     */
    public Cursor<Map.Entry<Object,Object>> getAllScanMap(String key) {
        try {
            return redisTemplate.opsForHash().scan(key, ScanOptions.NONE);
        } catch (Exception e) {
            throw new ThinkRPCException(CodeEnum.REDIS_EXCEPTION);
        }
    }

    /**
     * 模糊删除field
     * @param key
     * @return
     * @author xy
     * @date 2020年1月14日
     */
    public Long delFields(Set<String> key) {
        try {
            return redisTemplate.delete(key);
        } catch (Exception e) {
            throw new ThinkRPCException(CodeEnum.REDIS_EXCEPTION);
        }
    }

    /**
     * 删除field
     * @param key
     * @return
     * @author xy
     * @date 2020年1月14日
     */
    public Boolean delete(String key) {
        try {
            return redisTemplate.delete(key);
        } catch (Exception e) {
            throw new ThinkRPCException(CodeEnum.REDIS_EXCEPTION);
        }
    }

    /**
     * 读取Hash所有键值对
     * @param key
     * @return
     * @author xy
     * @date 2020年1月14日
     */
    public Map<Object,Object> getHashMap(String key) {
        try {
            return redisTemplate.opsForHash().entries(key);
        } catch (Exception e) {
            throw new ThinkRPCException(CodeEnum.REDIS_EXCEPTION);
        }
    }
    /**
     * 删除Hash字段
     * @param key
     * @return
     * @author xy
     * @date 2020年1月14日
     */
    public Long delHashField(String key,String...field) {
        try {
            return redisTemplate.opsForHash().delete(key, field);
        } catch (Exception e) {
            throw new ThinkRPCException(CodeEnum.REDIS_EXCEPTION);
        }
    }
    /**
     * 删除Hash
     * @param key
     * @return
     * @author xy
     * @date 2020年1月14日
     */
    public Boolean delHash(String key) {
        try {
            return redisTemplate.delete(key);
        } catch (Exception e) {
            throw new ThinkRPCException(CodeEnum.REDIS_EXCEPTION);
        }
    }
    /**
     * 设置Hash字段以及生存时间
     * @param key
     * @return
     * @author xy
     * @date 2020年1月14日
     */
    /**
     * SETEX 是一个原子性(atomic)操作，
     关联值和设置生存时间两个动作会在同一时间内完成,
     该命令在 Redis 用作缓存时，非常实用。
     SET key value
     EXPIRE key seconds  # 设置生存时间
     */
/*    public String setExpiredHash(String key,int expire,String value) {
        try {
            return redisTemplate.setex.setex(key, expire,value);
        } catch (Exception e) {
            throw new ThinkRPCException(CodeEnum.REDIS_EXCEPTION);
        }
    }*/
    /**
     * 存储Hash键值对
     * @param key
     * @return
     * @author xy
     * @date 2020年1月14日
     */
    public Boolean setHashValue(String key,String field,String value) {
        try {
            return redisTemplate.opsForHash().putIfAbsent(key,field,value);
        } catch (Exception e) {
            throw new ThinkRPCException(CodeEnum.REDIS_EXCEPTION);
        }
    }
    /**
     * 存储键值对
     * @param key
     * @return
     * @author xy
     * @date 2020年1月14日
     */
    public Boolean setIfAbsent(String key,Object value) {
        try {
            return redisTemplate.opsForValue().setIfAbsent(key,value);
        } catch (Exception e) {
            throw new ThinkRPCException(CodeEnum.REDIS_EXCEPTION);
        }
    }
    /**
     * 存储键值对
     * @param key
     * @return
     * @author xy
     * @date 2020年1月14日
     */
    public void set(String key,Object value) {
        try {
             redisTemplate.opsForValue().set(key,value);
        } catch (Exception e) {
            throw new ThinkRPCException(CodeEnum.REDIS_EXCEPTION);
        }
    }
    /**
     * 判断Hash的field是否存在
     * @param key
     * @return
     * @author xy
     * @date 22020年1月14日
     */
    public Boolean hashFieldExist (String key,String field) {
        try {
            return redisTemplate.opsForHash().hasKey(key,field);
        } catch (Exception e) {
            throw new ThinkRPCException(CodeEnum.REDIS_EXCEPTION);
        }
    }
}
