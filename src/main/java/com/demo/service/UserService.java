package com.demo.service;

import com.demo.pojo.User;

/**
 * @Program: Javademo
 * @ClassName: UserService
 * @Author: *****
 * @Copyright ****个人所有
 * @Date: 2021-11-03 16:43
 * @Description: ${创建类时填写描述信息}
 * @Version: V1.0
 */
public interface UserService {
    User login(int uid, String pwd);
    User findUser(int uid);
    int addUser(int uid,String uname,String pwd);
}
