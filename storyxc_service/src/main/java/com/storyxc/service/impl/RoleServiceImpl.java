package com.storyxc.service.impl;

import com.github.pagehelper.PageInfo;
import com.storyxc.mapper.RoleDao;
import com.storyxc.pojo.QueryPageBean;
import com.storyxc.pojo.Role;
import com.storyxc.service.RoleService;
import com.storyxc.util.PageHelperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public PageInfo<Role> findPage(QueryPageBean queryPageBean) {
        PageHelperUtils.startPage(queryPageBean);
        List<Role> roleList = roleDao.findPage(queryPageBean.getQueryString());
        return new PageInfo<>(roleList);
    }

    @Override
    @Transactional
    public void addRole(Role role) {
        roleDao.addRole(role);
        if (role.getMenuNums() != null && role.getMenuNums().size() > 0) {
            roleDao.setRoleMenus(role);
        }
        if (role.getPermissionNums() != null && role.getPermissionNums().size() > 0) {
            roleDao.setRolePermissions(role);
        }
    }

    @Override
    @Transactional
    public void deleteRole(Integer id) {
        roleDao.deleteRole(id);
        roleDao.deleteRolePermission(id);
        roleDao.deleteRoleMenu(id);
    }

    @Override
    public Role getRoleById(Integer id) {
        return roleDao.getRoleById(id);
    }

    @Override
    @Transactional
    public void editRole(Role role) {
        roleDao.editRole(role);
        roleDao.deleteRoleMenu(role.getId());
        roleDao.deleteRolePermission(role.getId());
        roleDao.setRoleMenus(role);
        roleDao.setRolePermissions(role);
    }

}
