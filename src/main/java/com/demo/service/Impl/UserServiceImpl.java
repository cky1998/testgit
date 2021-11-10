package com.demo.service.Impl;

import com.demo.dao.Impl.UserDaoImpl;
import com.demo.dao.UserDao;
import com.demo.pojo.User;
import com.demo.service.UserService;

/**
 * @Program: Javademo
 * @ClassName: UserServiceImpl
 * @Author: *****
 * @Copyright ****个人所有
 * @Date: 2021-11-03 16:44
 * @Description: ${创建类时填写描述信息}
 * @Version: V1.0
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();
    @Override
    public User login(int uid, String pwd) {
        return userDao.loginUser(uid,pwd);
    }

    @Override
    public User findUser(int uid) {
        return userDao.registerUser(uid);
    }

    @Override
    public int addUser(int uid, String uname, String pwd) {
        return userDao.addUser(uid,uname,pwd);
    }
}
