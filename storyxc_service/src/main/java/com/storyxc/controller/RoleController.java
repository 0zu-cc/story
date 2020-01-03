package com.storyxc.controller;

import com.storyxc.entity.Result;
import com.storyxc.entity.StatusCode;
import com.storyxc.pojo.Role;
import com.storyxc.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
