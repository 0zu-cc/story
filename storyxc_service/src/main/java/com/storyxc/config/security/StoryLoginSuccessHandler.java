package com.storyxc.config.security;

import com.storyxc.mapper.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xc
 * @description 登录成功后的处理
 * @createdTime 2020/7/18 22:15
 */
@Component
public class StoryLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private UserDao userDao;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        //记录登录最近登录时间和ip地址
        User user = (User) authentication.getPrincipal();
        String username = user.getUsername();
        if (StringUtils.isEmpty(username)) {
            throw new IllegalArgumentException("非法用户名");
        }
        com.storyxc.pojo.User curUser = userDao.findUserByUsername(username);
        if (curUser != null) {
            String remoteAddr = httpServletRequest.getRemoteAddr();
            userDao.updateLoginTimeByName(username,remoteAddr);
        }


        super.onAuthenticationSuccess(httpServletRequest,httpServletResponse,authentication);
    }
}
