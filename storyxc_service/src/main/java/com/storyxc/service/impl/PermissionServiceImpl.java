package com.storyxc.service.impl;

import com.github.pagehelper.PageInfo;
import com.storyxc.mapper.PermissionDao;
import com.storyxc.pojo.Permission;
import com.storyxc.pojo.QueryPageBean;
import com.storyxc.service.PermissionService;
import com.storyxc.util.PageHelperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Xc
 * @description
 * @createdTime 2020/1/6 14:19
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public PageInfo<Permission> findPage(QueryPageBean queryPageBean) {
        PageHelperUtil.startPage(queryPageBean);
        List<Permission> permissionList = permissionDao.findPage(queryPageBean.getQueryString());
        return new PageInfo<Permission>(permissionList);
    }

    @Override
    public void addPermission(Permission permission) {
        permissionDao.addPermission(permission);
    }

    @Override
    public void deletePermission(Integer id) {
        permissionDao.deletePermission(id);
    }

    @Override
    public void deleteRolePermission(Integer id) {
        permissionDao.deleteRolePermission(id);
    }

    @Override
    public Permission getPermissionById(Integer id) {
        return permissionDao.getPermissionById(id);
    }

    @Override
    public void editPermission(Permission permission) {
        permissionDao.editPermission(permission);
    }
}
