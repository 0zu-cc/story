package com.storyxc.service.impl;

import com.github.pagehelper.PageInfo;
import com.storyxc.mapper.UserDao;
import com.storyxc.pojo.QueryPageBean;
import com.storyxc.pojo.User;
import com.storyxc.service.UserService;
import com.storyxc.util.DateUtil;
import com.storyxc.util.PageHelperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
        user.setCreatedTime(DateUtil.parseDateToString(new Date()));
        userDao.addUser(user);
        if (roleIds != null && roleIds.length > 0) {
            for (Integer roleId : roleIds) {
                userDao.setUserRole(user.getId(), roleId);
            }
        }
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public PageInfo<User> queryUserList(QueryPageBean queryPageBean) {
        PageHelperUtil.startPage(queryPageBean);
        List<User> userList = userDao.queryUserList(queryPageBean.getQueryString());
        return new PageInfo<User>(userList);
    }

    @Override
    public User queryUserById(Integer id) {
        return userDao.queryUserById(id);
    }

    @Override
    public List<Integer> getRoleIdsByUserId(Integer id) {
        return userDao.getRoleIdsByUserId(id);
    }

    @Override
    public void delete(Integer id) {
        userDao.delete(id);
        userDao.deleteUserRole(id);
    }

    @Override
    public void editUser(User user, Integer[] roleIds) {
        userDao.editUser(user);
        userDao.deleteUserRole(user.getId());
        if (roleIds != null && roleIds.length > 0) {
            for (Integer roleId : roleIds) {
                userDao.setUserRole(user.getId(), roleId);
            }
        }

    }

    @Override
    public User findUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }
}
