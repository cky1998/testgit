package com.demo.dao.Impl;

import com.demo.dao.UserDao;
import com.demo.pojo.User;
import com.demo.utils.DruidUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @Program: Javademo
 * @ClassName: UserDaoImpl
 * @Author: *****
 * @Copyright ****个人所有
 * @Date: 2021-11-03 16:41
 * @Description: ${创建类时填写描述信息}
 * @Version: V1.0
 */
public class UserDaoImpl implements UserDao {

    private final JdbcTemplate jdbcTemplate = new JdbcTemplate(DruidUtil.getDataSource());

    @Override
    public User registerUser(int uid) {
        String sql = "select * from user where uid = ?";
        List<User> list = jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(User.class),uid);
        for (User user : list) {
            System.out.println(user.getClass().toString());
            return user;
        }
        return null;
    }

    @Override
    public int addUser(int uid,String uname,String pwd) {
        String sql = "insert into user(uid,username,pwd) values(?,?,?)";
        int count = jdbcTemplate.update(sql, uid, uname, pwd);
        return count;
    }

    @Override
    public User loginUser(int uid, String pwd) {
        String sql = "select * from user where uid = ? and pwd = ?";
        List<User> list = jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(User.class) ,uid,pwd);
        for (User user : list) {
            return user;
        }
        return null;
    }
}
