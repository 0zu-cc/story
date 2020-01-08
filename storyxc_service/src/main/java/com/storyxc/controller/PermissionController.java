package com.storyxc.controller;

import com.github.pagehelper.PageInfo;
import com.storyxc.entity.Result;
import com.storyxc.entity.StatusCode;
import com.storyxc.pojo.Permission;
import com.storyxc.pojo.QueryPageBean;
import com.storyxc.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Xc
 * @description
 * @createdTime 2020/1/6 14:18
 */
@RestController
@RequestMapping("/story/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/all")
    public Result getAllPermissions(){
        List<Permission> permissionList = permissionService.getAllPermissions();
        return new Result(true,StatusCode.OK,"查询所有权限成功",permissionList);
    }

    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        PageInfo<Permission> result = permissionService.findPage(queryPageBean);
        return new Result(true, StatusCode.OK,"查询权限列表成功",result);
    }

    @PostMapping
    public Result addPermission(@RequestBody Permission permission){
        permissionService.addPermission(permission);
        return new Result(true,StatusCode.OK,"新增权限成功");
    }

    @DeleteMapping
    @Transactional
    public Result deletePermission(Integer id){
        permissionService.deletePermission(id);
        permissionService.deleteRolePermission(id);
        return new Result(true,StatusCode.OK,"删除权限成功");
    }

    @GetMapping
    public Result getPermissionById(Integer id){
        Permission permission = permissionService.getPermissionById(id);
        return new Result(true,StatusCode.OK,"查询权限成功",permission);
    }

    @PutMapping
    public Result editPermission(@RequestBody Permission permission){
        permissionService.editPermission(permission);
        return new Result(true,StatusCode.OK,"编辑权限成功");
    }
}
