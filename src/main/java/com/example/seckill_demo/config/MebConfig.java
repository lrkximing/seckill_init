package com.example.seckill_demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

//MVC配置类（每次Controller入参之前判断用户信息）
@Configuration
@EnableWebMvc
public class MebConfig implements WebMvcConfigurer {
    @Autowired
    private UserArgumentResolvers userArgumentResolvers;
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
       resolvers.add(userArgumentResolvers);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
       registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }
}
