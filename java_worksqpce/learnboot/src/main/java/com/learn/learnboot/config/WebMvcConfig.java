package com.learn.learnboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

//@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    /**
     * 视图解析配置
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("demo.html").setViewName("demo");
        registry.addViewController("fun.html").setViewName("fun");
        registry.addViewController("fragment.html").setViewName("fragment");
        registry.addViewController("/login.html").setViewName("login");
    }
    /*将解析器注入到容器中*/
    @Bean
    public LocaleResolver localeResolver(){
        return new NativeLocaleResolver();
    }

    protected static class NativeLocaleResolver implements LocaleResolver{

        @Override
        public Locale resolveLocale(HttpServletRequest request) {
            Locale locale = null;
            String language = request.getParameter("language");
            if (!StringUtils.isEmpty(language)){
                String[] param = language.split("_");
                locale = new Locale(param[0],param[1]);
            }
            return locale;
        }

        @Override
        public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

        }
    }
}
