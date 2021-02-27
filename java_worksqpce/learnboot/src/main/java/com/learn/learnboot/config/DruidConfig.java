package com.learn.learnboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.servlet.Servlet;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//@Configuration
public class DruidConfig {
    //手动注入 yml druid相关参数配置
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource getDataSource(){
        return new DruidDataSource();
    }
    //监控信息配置
    @Bean
    public ServletRegistrationBean druidServletRegistration(){
        ServletRegistrationBean<Servlet> registrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        Map<String,String> initParam = new HashMap<>();
        initParam.put("loginUsername","admin");
        initParam.put("loginPassword","123456");
        //后台允许谁可以访问
//        initParam.put("allow","localhost");//locahost 只有本机可以访问
//        initParam.put("allow", "");//为空或者为null时，表示允许所有访问
        registrationBean.setInitParameters(initParam);
        return registrationBean;
    }
    //web监控配置
    //WebStatFilter：用于配置Web和Druid数据源之间的管理关联监控统计
    @Bean
    public FilterRegistrationBean druidWebStatFilter(){
        FilterRegistrationBean filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new WebStatFilter());

        //exclusions 过滤静态资源请求 不做监控
        Map<String,String> initParams = new HashMap<>();
        initParams.put("exclusions","*.js,*.css,/druid/*");
        // /* 表示过滤所有请求
        filterFilterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
        return filterFilterRegistrationBean;
    }
}
