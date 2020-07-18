package com.storyxc.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Xc
 * @description
 * @createdTime 2019/12/19 15:02
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //开启注解支持
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService storyUserDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private StoryLoginSuccessHandler loginSuccessHandler;

    @Autowired
    private StoryLogoutSuccessHandler logOutSuccessHandler;

    /***
     * 采用BCryptPasswordEncoder对密码进行编码
     * @return
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()// 该方法所返回的对象的方法来配置请求级别的安全细节
          // .antMatchers("/page/editor.html", "/page/management.html","/editor","/management").authenticated()//编辑器和管理后台需要拦截
           .antMatchers("/editor","/management","/editor/**").hasAnyRole("ADMIN")
           .antMatchers("/login").permitAll() // 登录页面不拦截
           .anyRequest().permitAll()
           .antMatchers(HttpMethod.POST, "/login").permitAll()// 对于登录路径不进行拦截
           .and().formLogin()// 配置登录页面
           .loginPage("/login")// 登录页面的访问路径;
           .successHandler(loginSuccessHandler)
           .loginProcessingUrl("/login")// 登录页面下表单提交的路径
           .failureUrl("/login?username_or_password_wrong")// 登录失败后跳转的路径,为了给客户端提示
           .and().logout().logoutUrl("/logout")// 用户退出
           .logoutSuccessHandler(logOutSuccessHandler)
           .permitAll()// 退出成功所访问的路径
           .and().exceptionHandling().accessDeniedPage("/403")
           .and()
           .csrf().disable()
           .headers().frameOptions()// 允许iframe内呈现。
           .sameOrigin();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(storyUserDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }
}
