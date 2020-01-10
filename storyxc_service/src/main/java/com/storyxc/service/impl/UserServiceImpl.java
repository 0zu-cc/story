package com.storyxc.service.impl;

import com.github.pagehelper.PageInfo;
import com.storyxc.mapper.UserDao;
import com.storyxc.pojo.QueryPageBean;
import com.storyxc.pojo.User;
import com.storyxc.service.UserService;
import com.storyxc.util.DateUtils;
import com.storyxc.util.PageHelperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author Xc
 * @description
 * @createdTime 2019/12/19 13:22
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private UserDao userDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void addUser(User user, Integer[] roleIds) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setCreatedTime(DateUtils.parseDateToString(new Date()));
        userDao.addUser(user);
        if (user.getHeadPic() != null && !"".equals(user.getHeadPic())) {
            redisTemplate.boundSetOps("userHeadPic").add(user.getHeadPic());
        }
        if (roleIds != null && roleIds.length > 0) {
            for (Integer roleId : roleIds) {
                userDao.setUserRole(user.getId(), roleId);
            }
        } else {
            userDao.setUserCommon(user.getId());
        }
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public PageInfo<User> queryUserList(QueryPageBean queryPageBean) {
        PageHelperUtils.startPage(queryPageBean);
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
    @Transactional
    public void delete(Integer id) {
        userDao.delete(id);
        userDao.deleteUserRole(id);
    }

    @Override
    @Transactional
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
