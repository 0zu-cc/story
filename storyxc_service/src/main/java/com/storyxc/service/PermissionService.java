package com.storyxc.service;

import com.github.pagehelper.PageInfo;
import com.storyxc.pojo.Permission;
import com.storyxc.pojo.QueryPageBean;

import java.util.List;

/**
 * @author Xc
 * @description
 * @createdTime 2020/1/6 14:18
 */
public interface PermissionService {
    PageInfo<Permission> findPage(QueryPageBean queryPageBean);

    void addPermission(Permission permission);

    void deletePermission(Integer id);

    void deleteRolePermission(Integer id);

    Permission getPermissionById(Integer id);

    void editPermission(Permission permission);

    List<Permission> getAllPermissions();
}
