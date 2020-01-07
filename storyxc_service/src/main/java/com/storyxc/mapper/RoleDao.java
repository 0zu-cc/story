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

    List<Role> findPage(String queryString);

    void addRole(Role role);

    void setRoleMenus(Role role);

    void setRolePermissions(Role role);

    void deleteRole(Integer id);

    void deleteRolePermission(Integer id);

    void deleteRoleMenu(Integer id);

    Role getRoleById(Integer id);
}
