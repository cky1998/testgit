package com.demo.dao;

import com.demo.pojo.User;

/**
 * @Program: Javademo
 * @ClassName: UserDao
 * @Author: *****
 * @Copyright ****个人所有
 * @Date: 2021-11-03 16:38
 * @Description: ${创建类时填写描述信息}
 * @Version: V1.0
 */
public interface UserDao {
    User registerUser(int uid);
    int addUser(int uid,String uname,String pwd);
    User loginUser(int uid, String pwd);
}
