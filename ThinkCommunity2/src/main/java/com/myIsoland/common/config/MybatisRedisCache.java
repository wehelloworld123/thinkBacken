package com.myIsoland.common.config;


import com.myIsoland.common.util.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import javax.swing.*;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
@Slf4j
public class MybatisRedisCache implements Cache {

    private Logger logger = LoggerFactory.getLogger(MybatisRedisCache.class);
    // 读写锁
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);

    //这里使用了redis缓存，使用springboot自动注入
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private String id;

    public MybatisRedisCache(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        if (redisTemplate == null) {
            //由于启动期间注入失败，只能运行期间注入，这段代码可以删除
            redisTemplate = (RedisTemplate<String, Object>) SpringUtils.getBean("functionDomainRedisTemplate");
        }
        if (value != null) {
            redisTemplate.opsForValue().set(key.toString(), value);
        }
    }

    @Override
    public Object getObject(Object key) {
        try {
            if (key != null) {
                return redisTemplate.opsForValue().get(key.toString());
            }
        } catch (Exception e) {
            logger.error("缓存出错 ");
        }
        return null;
    }

    @Override
    public Object removeObject(Object key) {
        if (key != null) {
            redisTemplate.delete(key.toString());
        }
        return null;
    }

    @Override
    public void clear() {
        logger.debug("清空缓存");
        if (redisTemplate == null) {
            redisTemplate = (RedisTemplate<String, Object>) SpringUtils.getBean("functionDomainRedisTemplate");
        }
        Set<String> keys = redisTemplate.keys("*:" + this.id + "*");
        if (!CollectionUtils.isEmpty(keys)) {
            redisTemplate.delete(keys);
        }
    }

    @Override
    public int getSize() {
        Long size = redisTemplate.execute((RedisCallback<Long>) RedisServerCommands::dbSize);
        return size.intValue();
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return this.readWriteLock;
    }

}
