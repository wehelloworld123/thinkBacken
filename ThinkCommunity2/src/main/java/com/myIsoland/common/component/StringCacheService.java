package com.myIsoland.common.component;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class StringCacheService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    /**
     * 新增一个字符串类型的值,key是键，value是值。
     *
     * set(K key, V value)
     */
    public void set(String key,String value) {
        // 存入永久数据
        stringRedisTemplate.opsForValue().set(key, value);

    }
    /**
     * 新增一个字符串类型的值,key是键，value是值。
     *
     * set(K key, V value)
     */
    public void setWithTime(String key,String value,Long time,TimeUnit unit) {

        // 也可以向redis里存入数据和设置缓存时间
        stringRedisTemplate.opsForValue().set(key, value, time, unit);
    }
    /**
     * 批量插入，key值存在会覆盖原值
     *
     * multiSet(Map<? extends K,? extends V> map)
     */
    public void multiSet(Map map) {
        stringRedisTemplate.opsForValue().multiSet(map);
    }

    /**
     *  批量插入，如果里面的所有key都不存在，则全部插入，返回true，如果其中一个在redis中已存在，全不插入，返回false
     *
     *  multiSetIfAbsent(Map<? extends K,? extends V> map)
     */
    public Boolean multiSetIfAbsent(Map map) {

        return stringRedisTemplate.opsForValue().multiSetIfAbsent(map);
    }

    /**
     * 如果不存在则插入，返回true为插入成功,false失败
     *
     * setIfAbsent(K key, V value)
     */
    public Boolean setIfAbsent(String key,String value) {
        return stringRedisTemplate.opsForValue().setIfAbsent(key, value);
    }
    /**
     * 获取值,key不存在返回null
     *
     * get(Object key)
     */
    public Object get() {
        return stringRedisTemplate.opsForValue().get("testMultiSet1");
    }

    /**
     * 批量获取，key不存在返回null
     *
     * multiGet(Collection<K> keys)
     */
    public List<String> multiGet(List<String> data) {
        return stringRedisTemplate.opsForValue().multiGet(data);
    }

    /**
     * 获取指定字符串的长度。
     *
     * size(K key)
     */
    public Long getLength(String key) {
        return stringRedisTemplate.opsForValue().size(key);
    }

    /**
     * 在原有的值基础上新增字符串到末尾。
     *
     * append(K key, String value)
     */
    public Integer append(String key,String value) {
        return stringRedisTemplate.opsForValue().append(key, value);
    }

    /**
     * 获取原来key键对应的值并重新赋新值
     *
     * getAndSet(K key, V value)
     */
    public String getAndSet(String key,String value) {
        return stringRedisTemplate.opsForValue().getAndSet(key, value);
    }

    /**
     * 获取指定key的值进行减1，如果value不是integer类型，会抛异常，如果key不存在会创建一个，默认value为0
     *
     * decrement(k key)
     */
    public Long decrement(String key) {
        return stringRedisTemplate.opsForValue().decrement(key);
    }

    /**
     * 获取指定key的值进行加1，如果value不是integer类型，会抛异常，如果key不存在会创建一个，默认value为0
     *
     * increment(k key)
     */
    public Long increment(String key) {
        return stringRedisTemplate.opsForValue().increment(key);
    }

    /**
     * 删除指定key,成功返回true，否则false
     *
     * delete(k key)
     */
    public Boolean delete(String key) {
        return stringRedisTemplate.opsForValue().getOperations().delete(key);
    }

    /**
     * 删除多个key，返回删除key的个数
     *
     * delete(k ...keys)
     */
    public Long deleteMulti(List<String> data) {
        return stringRedisTemplate.opsForValue().getOperations().delete(data);
    }

}
