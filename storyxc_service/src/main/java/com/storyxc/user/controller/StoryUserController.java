package com.storyxc.user.controller;

import com.github.pagehelper.PageInfo;
import com.storyxc.entity.Result;
import com.storyxc.entity.StatusCode;
import com.storyxc.pojo.StoryUser;
import com.storyxc.user.service.StoryUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:Xc
 * @Description:
 * @Date
 *****/

@RestController
@RequestMapping("/storyUser")
@CrossOrigin
public class StoryUserController {

    @Autowired
    private StoryUserService storyUserService;

    /***
     * StoryUser分页条件搜索实现
     * @param storyUser
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false)  StoryUser storyUser, @PathVariable  int page, @PathVariable  int size){
        //调用StoryUserService实现分页条件查询StoryUser
        PageInfo<StoryUser> pageInfo = storyUserService.findPage(storyUser, page, size);
        return new Result(true, StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * StoryUser分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用StoryUserService实现分页查询StoryUser
        PageInfo<StoryUser> pageInfo = storyUserService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索
     * @param storyUser
     * @return
     */
    @PostMapping(value = "/search" )
    public Result<List<StoryUser>> findList(@RequestBody(required = false)  StoryUser storyUser){
        //调用StoryUserService实现条件查询StoryUser
        List<StoryUser> list = storyUserService.findList(storyUser);
        return new Result<List<StoryUser>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        //调用StoryUserService实现根据主键删除
        storyUserService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改StoryUser数据
     * @param storyUser
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    public Result update(@RequestBody  StoryUser storyUser,@PathVariable Integer id){
        //设置主键值
        storyUser.setId(id);
        //调用StoryUserService实现修改StoryUser
        storyUserService.update(storyUser);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增StoryUser数据
     * @param storyUser
     * @return
     */
    @PostMapping
    public Result add(@RequestBody   StoryUser storyUser){
        //调用StoryUserService实现添加StoryUser
        storyUserService.add(storyUser);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询StoryUser数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<StoryUser> findById(@PathVariable Integer id){
        //调用StoryUserService实现根据主键查询StoryUser
        StoryUser storyUser = storyUserService.findById(id);
        return new Result<StoryUser>(true,StatusCode.OK,"查询成功",storyUser);
    }

    /***
     * 查询StoryUser全部数据
     * @return
     */
    @GetMapping
    public Result<List<StoryUser>> findAll(){
        //调用StoryUserService实现查询所有StoryUser
        List<StoryUser> list = storyUserService.findAll();
        return new Result<List<StoryUser>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
