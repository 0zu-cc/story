package com.storyxc.controller;

import com.github.pagehelper.PageInfo;
import com.storyxc.entity.Result;
import com.storyxc.entity.StatusCode;
import com.storyxc.pojo.Menu;
import com.storyxc.pojo.QueryPageBean;
import com.storyxc.pojo.User;
import com.storyxc.service.MenuService;
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
 * @createdTime 2020/1/6 15:50
 */
@RestController
@RequestMapping("/story/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private UserService userService;

    @GetMapping("/getMenusByUsername")
    public Result getMenusByUsername(String username){
        List<Menu> menuList = menuService.getMenusByUsername(username);
        return new Result(true,StatusCode.OK,"查询用户菜单列表成功",menuList);
    }

    @PreAuthorize("hasAnyAuthority('USER_QUERY')")
    @GetMapping("/getCurrentUser")
    public Result getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findUserByUsername(username);
        return new Result(true, StatusCode.OK,"获取当前用户成功",user);
    }

    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        PageInfo<Menu> result = menuService.findPage(queryPageBean);
        return new Result(true,StatusCode.OK,"查询菜单列表成功",result);
    }

    @GetMapping("/all")
    public Result getAllMenu(){
        List<Menu> menuList = menuService.getAllMenus();
        return new Result(true,StatusCode.OK,"查询所有菜单成功",menuList);
    }

    @PostMapping
    public Result addMenu(@RequestBody Menu menu){
        menuService.addMenu(menu);
        return new Result(true,StatusCode.OK,"新增菜单成功");
    }

    @GetMapping
    public Result getMenuById(Integer id){
        Menu menu = menuService.getMenuById(id);
        return new Result(true,StatusCode.OK,"查询菜单成功",menu);
    }

    @PutMapping
    public Result editMenu(@RequestBody Menu menu){
        menuService.editMenu(menu);
        return new Result(true,StatusCode.OK,"更新菜单成功");
    }

    @DeleteMapping
    public Result deleteMenu(Integer id){
        menuService.deleteMenu(id);
        return new Result(true,StatusCode.OK,"删除菜单成功");
    }
}
