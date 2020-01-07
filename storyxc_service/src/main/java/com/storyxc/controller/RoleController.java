package com.storyxc.controller;

import com.github.pagehelper.PageInfo;
import com.storyxc.entity.Result;
import com.storyxc.entity.StatusCode;
import com.storyxc.pojo.QueryPageBean;
import com.storyxc.pojo.Role;
import com.storyxc.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Xc
 * @description
 * @createdTime 2020/1/3 16:03
 */
@RestController
@RequestMapping("/story/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public Result queryAllRoles(){
        List<Role> roleList = roleService.queryAllRoles();
        return new Result(true, StatusCode.OK,"查询所有角色成功",roleList);
    }

    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        PageInfo<Role> result = roleService.findPage(queryPageBean);
        return new Result(true,StatusCode.OK,"查询角色列表成功",result);
    }

    @PostMapping
    public Result addRole(@RequestBody Role role){
        roleService.addRole(role);
        return new Result(true,StatusCode.OK,"新建角色成功");
    }

    @DeleteMapping
    public Result deleteRole(Integer id){
        roleService.deleteRole(id);
        return new Result(true,StatusCode.OK,"删除角色成功");
    }


    @GetMapping("/getRole")
    public Result getRole(Integer id){
        Role role = roleService.getRoleById(id);
        return new Result(true,StatusCode.OK,"查询角色成功",role);
    }

}
