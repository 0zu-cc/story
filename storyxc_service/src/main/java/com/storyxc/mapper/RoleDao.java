package com.storyxc.mapper;

import com.storyxc.pojo.Role;

import java.util.List;

/**
 * @author Xc
 * @description
 * @createdTime 2020/1/3 16:06
 */
public interface RoleDao {
    List<Role> queryAllRoles();
}
