package com.myIsoland.common.component;


import com.myIsoland.common.util.ObjectUtils;
import com.myIsoland.enums.CodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


import java.io.Serializable;
import java.util.Map;
import java.util.Set;


public class JedisCacheService {


/*    @Autowired
    private JedisPool jedisPool;

    *//**
     * 存储字符串键值对
     * @param key
     * @param value
     * @return
     * @author hw
     * @date 2018年12月14日
     *//*
    public String set(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.set(key, value);
        } catch (Exception e) {
            throw new ThinkRPCException(CodeEnum.REDIS_EXCEPTION);
        } finally {
            jedis.close();
        }
    }

    *//**
     * 得到对应键的字符串值
     * @param key
     * @return
     * @author hw
     * @date 2018年12月14日
     *//*
    public String get(String key) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.get(key);
        } catch (Exception e) {
            throw new ThinkRPCException(CodeEnum.REDIS_EXCEPTION);
        } finally {
            jedis.close();
        }
    }

    *//**
     * 删除字符串键值对
     * @param key
     * @return
     * @author hw
     * @date 2018年12月14日
     *//*
    public Long del(String key) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.del(key);
        } catch (Exception e) {
            throw new ThinkRPCException(CodeEnum.REDIS_EXCEPTION);
        } finally {
            jedis.close();
        }
    }

    *//**
     * 存储对象
     * @param key
     * @param value
     * @return
     * @author hw
     * @date 2018年12月14日
     *//*
    public String setObject(String key, Object value) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.set(key.getBytes(), ObjectUtils.serialize(value));
        } catch (Exception e) {
            throw new ThinkRPCException(CodeEnum.REDIS_EXCEPTION);
        } finally {
            jedis.close();
        }
    }

    *//**
     * 得到对应键的对象
     * @param key
     * @return
     * @author hw
     * @date 2018年12月14日
     *//*
    public Object getObject(String key) {
        Jedis jedis = jedisPool.getResource();
        try {
            byte[] byteArr =  jedis.get(key.getBytes());
            return ObjectUtils.unSerialize(byteArr);
        } catch (Exception e) {
            throw new ThinkRPCException(CodeEnum.REDIS_EXCEPTION);
        } finally {
            jedis.close();
        }
    }

    *//**
     * 读取Hash单个field的值
     * @param key,field
     * @return
     * @author xy
     * @date 22020年1月14日
     *//*
    public String getHashField (String key,String field) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.hget(key,field);
        } catch (Exception e) {
            throw new ThinkRPCException(CodeEnum.REDIS_EXCEPTION);
        } finally {
            jedis.close();
        }
    }
    *//**
     * 直接修改Hash已存在的值 如果field已存在则执行修改,并返回0
     * @param key,field,value
     * @return
     * @author xy
     * @date 22020年1月14日
     *//*
    public Long modifyHashValue (String key,String field,String value) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.hset(key,field,value);
        } catch (Exception e) {
            throw new ThinkRPCException(CodeEnum.REDIS_EXCEPTION);
        } finally {
            jedis.close();
        }
    }
    *//**
     * 批量设置Hash的Map集合
     * @param key
     * @return
     * @author xy
     * @date 2020年1月14日
     *//*
    public String setHashMap(String key, Map map) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.hmset(key,map);
        } catch (Exception e) {
            throw new ThinkRPCException(CodeEnum.REDIS_EXCEPTION);
        } finally {
            jedis.close();
        }
    }
    *//**
     * 读取Hash所有field
     * @param key
     * @return
     * @author xy
     * @date 2020年1月14日
     *//*
    public Set<String> getHashFields(String key) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.hkeys(key);
        } catch (Exception e) {
            throw new ThinkRPCException(CodeEnum.REDIS_EXCEPTION);
        } finally {
            jedis.close();
        }
    }

    *//**
     * 模糊查询field
     * @param key
     * @return
     * @author xy
     * @date 2020年1月14日
     *//*
    public Set<String> getLikeFields(String key) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.keys(key);
        } catch (Exception e) {
            throw new ThinkRPCException(CodeEnum.REDIS_EXCEPTION);
        } finally {
            jedis.close();
        }
    }

    *//**
     * 读取Hash所有键值对
     * @param key
     * @return
     * @author xy
     * @date 2020年1月14日
     *//*
    public Map<String,String> getHashMap(String key) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.hgetAll(key);
        } catch (Exception e) {
            throw new ThinkRPCException(CodeEnum.REDIS_EXCEPTION);
        } finally {
            jedis.close();
        }
    }
    *//**
     * 删除Hash字段
     * @param key
     * @return
     * @author xy
     * @date 2020年1月14日
     *//*
    public Long delHashField(String key,String...field) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.hdel(key, field);
        } catch (Exception e) {
            throw new ThinkRPCException(CodeEnum.REDIS_EXCEPTION);
        } finally {
            jedis.close();
        }
    }
    *//**
     * 删除Hash
     * @param key
     * @return
     * @author xy
     * @date 2020年1月14日
     *//*
    public Long delHash(String key) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.hdel(key);
        } catch (Exception e) {
            throw new ThinkRPCException(CodeEnum.REDIS_EXCEPTION);
        } finally {
            jedis.close();
        }
    }
    *//**
     * 设置Hash字段以及生存时间
     * @param key
     * @return
     * @author xy
     * @date 2020年1月14日
     *//*
    *//**
     * SETEX 是一个原子性(atomic)操作，
     关联值和设置生存时间两个动作会在同一时间内完成,
     该命令在 Redis 用作缓存时，非常实用。
     SET key value
     EXPIRE key seconds  # 设置生存时间
     *//*
    public String setExpiredHash(String key,int expire,String value) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.setex(key, expire,value);
        } catch (Exception e) {
            throw new ThinkRPCException(CodeEnum.REDIS_EXCEPTION);
        } finally {
            jedis.close();
        }
    }
    *//**
     * 存储Hash键值对
     * @param key
     * @return
     * @author xy
     * @date 2020年1月14日
     *//*
    public Long setHashValue(String key,String field,String value) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.hsetnx(key,field,value);
        } catch (Exception e) {
            throw new ThinkRPCException(CodeEnum.REDIS_EXCEPTION);
        } finally {
            jedis.close();
        }
    }
    *//**
     * 判断Hash的field是否存在
     * @param key
     * @return
     * @author xy
     * @date 22020年1月14日
     *//*
    public Boolean hashFieldExist (String key,String field) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.hexists(key,field);
        } catch (Exception e) {
            throw new ThinkRPCException(CodeEnum.REDIS_EXCEPTION);
        } finally {
            jedis.close();
        }
    }*/
}