package com.storyxc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Xc
 * @description
 * @createdTime 2019/12/19 15:02
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

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
           .antMatchers("/editor","/management").hasRole("ADMIN")
           .antMatchers("/login").permitAll() // 登录页面不拦截
           .anyRequest().permitAll() //其他所有不拦截
           .antMatchers(HttpMethod.POST, "/login").permitAll()// 对于登录路径不进行拦截
           .and().formLogin()// 配置登录页面
           .loginPage("/login")// 登录页面的访问路径;
           .loginProcessingUrl("/login")// 登录页面下表单提交的路径
           .failureUrl("/login?paramserror=true")// 登录失败后跳转的路径,为了给客户端提示
           .and().logout().logoutUrl("/logout")// 用户退出
           .permitAll().logoutSuccessUrl("/login?logout=true")/// 退出成功所访问的路径
           .and().exceptionHandling().accessDeniedPage("/403")
           .and()
           .csrf().disable()
           .headers().frameOptions()// 允许iframe内呈现。
           .sameOrigin();
    }
}
