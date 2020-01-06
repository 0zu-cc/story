package com.storyxc.service;

import com.github.pagehelper.PageInfo;
import com.storyxc.pojo.QueryPageBean;
import com.storyxc.pojo.User;

import java.util.List;

/**
 * @author Xc
 * @description
 * @createdTime 2019/12/19 13:22
 */
public interface UserService {
    void addUser(User user, Integer[] roleIds);

    User findByUsername(String username);

    PageInfo<User> queryUserList(QueryPageBean queryPageBean);

    User queryUserById(Integer id);

    List<Integer> getRoleIdsByUserId(Integer id);

    void delete(Integer id);

    void editUser(User user, Integer[] roleIds);

    User findUserByUsername(String username);
}
