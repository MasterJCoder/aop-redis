package dao;

import annotation.CacheAble;
import annotation.NonCache;
import org.springframework.stereotype.Repository;
import pojo.User;

/**
 * Created by lizhihui on 2017-02-19 13:29.
 */
@Repository
public class UserDao implements IUserDao{

    private User user = new User("李志辉", 22);

    @CacheAble(key = "user", type = User.class)
    public User findById(Integer id) {
        if (id.equals(1)) {
            return user;
        }
        return null;
    }

    @NonCache(key = "user", type = User.class)
    public void updateUser(Integer id) {
        if (id.equals(1)) {
            user.setAge(23);
        }
    }
}
