package com.storyxc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Xc
 * @description
 * @createdTime 2019/12/23 20:44
 */
@Configuration
public class InterceptorConfigurer implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {

    }
}
