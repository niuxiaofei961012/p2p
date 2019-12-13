package com.p2p.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public <T> void set(String key,T obj,int seconds){

        String json = objToJson(obj);

        stringRedisTemplate.opsForValue().set(key,json,seconds, TimeUnit.SECONDS);
    }

    private <T> String objToJson(T obj) {
        Class<?> clazz = obj.getClass();
        if (clazz==String.class){
            return (String)obj;
        }
        String s = JSON.toJSONString(obj);
        return s;
    }

    public <T> T get(String key,Class<T> clazz){
        if (key==null){
            return null;
        }
        String s = stringRedisTemplate.opsForValue().get(key);
        if (s==null){
            return null;
        }
        T t = jsonToObj(s, clazz);
        return t;
    }

    private <T> T jsonToObj(String s,Class<T> clazz) {
        if (clazz==String.class){
            return (T)s;
        }

        T obj = JSON.parseObject(s, clazz);
        return obj;
    }
}
