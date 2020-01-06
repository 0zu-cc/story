package com.storyxc.mapper;

import com.storyxc.pojo.Permission;

import java.util.List;

/**
 * @author Xc
 * @description
 * @createdTime 2020/1/6 14:19
 */
public interface PermissionDao {
    List<Permission> findPage(String queryString);

    void addPermission(Permission permission);

    void deletePermission(Integer id);

    void deleteRolePermission(Integer id);

    Permission getPermissionById(Integer id);

    void editPermission(Permission permission);
}
