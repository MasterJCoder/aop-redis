package cache;

import org.springframework.data.redis.core.RedisTemplate;
import util.JsonUtils;

import javax.annotation.Resource;

/**
 * Created by lizhihui on 2017-02-19 12:57.
 */
public class JedisSingleClient implements JedisClient {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public void hset(String key, String field, Object object) {
        redisTemplate.opsForHash().put(key, field, JsonUtils.objectToJson(object));
    }

    public String hget(String key, String field) {
        return (String) redisTemplate.opsForHash().get(key, field);
    }

    public void hdel(String key, String field) {
        redisTemplate.opsForHash().delete(key, field);
    }
}
