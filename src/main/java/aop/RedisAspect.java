package aop;

import annotation.CacheAble;
import annotation.NonCache;
import cache.JedisClient;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import util.JsonUtils;

import javax.annotation.Resource;

/**
 * Created by lizhihui on 2017-02-19 12:24.
 */
@Component
@Aspect
public class RedisAspect {
    @Resource
    private JedisClient jedisClient;

    @Before("@annotation(nonCache) && args(id)")
    public void before(JoinPoint jp, Integer id, NonCache nonCache) {
        String key = nonCache.key();
        System.out.println("清除缓存");
        jedisClient.hdel(key, id.toString());
    }

    @Around("@annotation(cacheAble)&& args(id)")
    public Object doAround(ProceedingJoinPoint pjp, Integer id, CacheAble cacheAble) {
        System.out.println("查找ID为:" + id + "的用户");
        String key = cacheAble.key();
        String hget = jedisClient.hget(key, id.toString());
        if (hget != null) {
            System.out.println("在缓存中找到了");
            return JsonUtils.jsonToPojo(hget, cacheAble.type());
        }
        System.out.println("没有在缓存中查到该用户信息");
        Object proceed = null;
        try {
            proceed = pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        if (proceed != null) {
            System.out.println("在数据库中找到了");
            jedisClient.hset(key, id.toString(), proceed);
            return proceed;
        }
        System.out.println("在数据库中也找不到");
        return null;
    }
}
