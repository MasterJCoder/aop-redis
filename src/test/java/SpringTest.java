import cache.JedisClient;
import dao.UserDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pojo.User;

import javax.annotation.Resource;

/**
 * Created by lizhihui on 2017-02-19 13:08.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-redis.xml"})
public class SpringTest {


    @Resource
    private JedisClient jedisClient;
    @Resource
    private UserDao userDao;

    @Before
    public void cleanCache() {
        jedisClient.hdel("user", Integer.toString(1));
    }

    @Test
    public void redisTest() {
        User user = userDao.findById(1);
        System.out.println(user);
        System.out.println("+++++++++++++++++华丽的分割线+++++++++++++++++");
        user = userDao.findById(1);
        System.out.println(user);
        System.out.println("+++++++++++++++++华丽的分割线+++++++++++++++++");
        userDao.updateUser(1);
        System.out.println("+++++++++++++++++华丽的分割线+++++++++++++++++");
        user = userDao.findById(1);
        System.out.println(user);

    }
}
