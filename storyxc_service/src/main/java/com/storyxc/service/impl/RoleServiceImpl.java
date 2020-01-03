package com.storyxc.service.impl;

import com.storyxc.mapper.RoleDao;
import com.storyxc.pojo.Role;
import com.storyxc.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author Xc
 * @description
 * @createdTime 2020/1/3 16:05
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;


    @Override
    public List<Role> queryAllRoles() {
        return roleDao.queryAllRoles();
    }
}
