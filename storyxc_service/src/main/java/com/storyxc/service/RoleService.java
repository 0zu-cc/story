package com.storyxc.service;

import com.github.pagehelper.PageInfo;
import com.storyxc.pojo.QueryPageBean;
import com.storyxc.pojo.Role;

import java.util.List;

/**
 * @author Xc
 * @description
 * @createdTime 2020/1/3 16:05
 */
public interface RoleService {
    List<Role> queryAllRoles();

    PageInfo<Role> findPage(QueryPageBean queryPageBean);

    void addRole(Role role);

    void deleteRole(Integer id);

    Role getRoleById(Integer id);

    void editRole(Role role);
}
