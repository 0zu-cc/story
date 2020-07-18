package com.storyxc.config.interceptor;

import com.storyxc.config.interceptor.StoryRequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private StoryRequestInterceptor storyRequestInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(storyRequestInterceptor)
                .addPathPatterns("/story/article/findPage/manage","/story/article","/story/article/edit/*","/story/permission/**","/story/role/**","/story/user/**","/story/menu/**")
                .excludePathPatterns("/story/user/register","/login");
    }
}
