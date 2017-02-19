package dao;

import pojo.User;

/**
 * Created by lizhihui on 2017-02-19 15:22.
 */
public interface IUserDao {
    User findById(Integer id);

    void updateUser(Integer id);
}
