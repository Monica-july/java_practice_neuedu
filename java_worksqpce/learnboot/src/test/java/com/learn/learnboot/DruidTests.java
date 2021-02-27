package com.learn.learnboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
public class DruidTests {
    @Autowired
    DataSource dataSource;
    @Test
    void context() throws SQLException {
        System.out.println(dataSource.getClass());
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        //查看dataSource 发现yml配置最大连接数等未生效
        //springboot 不自动注入 druid配置
        //添加配置druidConfig 手动注入
        //再次运行  查看配置生效

    }
}
