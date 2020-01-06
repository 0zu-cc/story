package com.storyxc.controller;

import com.github.pagehelper.PageInfo;
import com.storyxc.entity.Result;
import com.storyxc.entity.StatusCode;
import com.storyxc.pojo.QueryPageBean;
import com.storyxc.pojo.User;
import com.storyxc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Xc
 * @description
 * @createdTime 2019/12/19 13:19
 */
@RestController
@RequestMapping("/story/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/loginStatus")
    public Result loginStatus(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        if ("anonymousUser".equals(username)){
            return new Result(false,StatusCode.OK,"用户未登录");
        }
        return new Result(true,StatusCode.OK,"查询登录状态成功",username);
    }


    @PostMapping
    public Result addUser(@RequestBody User user, Integer[] roleIds) {
        userService.addUser(user,roleIds);
        return new Result(true, StatusCode.OK,"新建用户成功");
    }

    @PreAuthorize("hasAnyAuthority('USER_QUERY')")
    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        PageInfo<User> result = userService.queryUserList(queryPageBean);
        return new Result(true,StatusCode.OK,"查询用户列表成功",result);
    }

    @PreAuthorize("hasAnyAuthority('USER_QUERY')")
    @GetMapping
    public Result queryUserById(Integer id){
        User user = userService.queryUserById(id);
        return new Result(true,StatusCode.OK,"查询用户信息成功",user);
    }

    @PostMapping("/getRoleIdsByUserId")
    public Result getRoleIdsByUserId(Integer id){
        List<Integer> roleIds = userService.getRoleIdsByUserId(id);
        return new Result(true,StatusCode.OK,"查询用户角色成功",roleIds);
    }

    @PreAuthorize("hasAnyAuthority('USER_DELETE')")
    @DeleteMapping
    public Result deleteUser(Integer id){
        userService.delete(id);
        return new Result(true,StatusCode.OK,"删除用户成功");
    }

    @PutMapping
    public Result edit(@RequestBody User user,Integer[] roleIds){
        userService.editUser(user,roleIds);
        return new Result(true,StatusCode.OK,"编辑用户信息成功");
    }

}
