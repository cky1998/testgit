package com.demo.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DruidUtil {
    private DruidUtil(){}

    private static DataSource dataSource;

    static {
        try {
            //1.加载配置文件
            Properties prop = new Properties();
            // 类加载器,文件路径参考的就是运行时的目录 classes
            //使用ClassLoader加载配置文件，获取字节输入流
            InputStream in = DruidUtil.class.getClassLoader().getResourceAsStream("db.properties");
            //FileInputStream in = new FileInputStream("/WEB-INF/classes/druid.properties");
            prop.load(in);
            //2.初始化连接池对象
            dataSource = DruidDataSourceFactory.createDataSource(prop);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 获取连接池对象
     */
    public static DataSource getDataSource() {
        return dataSource;
    }

    /**
     * 获取连接Connection对象
     */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
