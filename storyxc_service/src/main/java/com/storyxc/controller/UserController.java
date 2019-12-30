package com.storyxc.controller;

import com.storyxc.entity.Result;
import com.storyxc.entity.StatusCode;
import com.storyxc.pojo.User;
import com.storyxc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
        Object principal = authentication.getPrincipal();
        String userName = principal.toString();
        if ("anonymousUser".equals(userName)){
            return new Result(false,StatusCode.OK,"用户未登录");
        }
        return new Result(true,StatusCode.OK,"查询登录状态成功",userName);
    }

    @PostMapping
    public Result addUser(@RequestBody User user, Integer[] roleIds) {
        userService.addUser(user,roleIds);
        return new Result(true, StatusCode.OK,"新建用户成功");
    }



}
