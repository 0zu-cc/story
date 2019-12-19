package com.storyxc.service;

import com.storyxc.pojo.User;

/**
 * @author Xc
 * @description
 * @createdTime 2019/12/19 13:22
 */
public interface UserService {
    void addUser(User user, Integer[] roleIds);

    User queryUserByUsername(String username);
}
