package com.storyxc.service.impl;

import com.storyxc.mapper.UserDao;
import com.storyxc.pojo.User;
import com.storyxc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Xc
 * @description
 * @createdTime 2019/12/19 13:22
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public void addUser(User user, Integer[] roleIds) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setCreatedTime(new Date());
        userDao.addUser(user);
        if (roleIds != null && roleIds.length > 0) {
            for (Integer roleId : roleIds) {
                userDao.setUserRole(user.getId(), roleId);
            }
        }
    }

    @Override
    public User queryUserByUsername(String username) {
        return userDao.queryUserByUsername(username);
    }
}
