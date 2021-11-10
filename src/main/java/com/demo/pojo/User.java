package com.demo.pojo;

/**
 * @Program: Javademo
 * @ClassName: User
 * @Author: *****
 * @Copyright ****个人所有
 * @Date: 2021-11-03 16:33
 * @Description: ${创建类时填写描述信息}
 * @Version: V1.0
 */
public class User {
    private int uid;
    private String username;
    private String pwd;

    public User() {
    }

    public User(int uid, String uname, String pwd) {
        this.uid = uid;
        this.username = uname;
        this.pwd = pwd;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String uname) {
        this.username = uname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
