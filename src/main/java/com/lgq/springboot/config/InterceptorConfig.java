package com.lgq.springboot.config;

import com.lgq.springboot.common.tokenHandler;
import org.apache.ibatis.parsing.TokenHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author LGQ
 * @createTime 2022/8/7 16:57
 * @description
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenHandler())
                //拦截所有请求,通过判断段token是否合法来决定是否需要登录
                .addPathPatterns("/**")
                .excludePathPatterns("/login", "/register", "**/import", "**/export");
    }

    @Bean
    public tokenHandler tokenHandler(){
        return new tokenHandler();
    }

}
