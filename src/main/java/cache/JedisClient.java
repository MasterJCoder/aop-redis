package cache;

/**
 * Created by lizhihui on 2017-02-19 12:16.
 */
public interface JedisClient {


    void hset(String key, String field, Object object);

    String hget(String key, String field);

    void hdel(String key, String field);

}
