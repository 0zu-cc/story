package com.storyxc.service;

import com.github.pagehelper.PageInfo;
import com.storyxc.pojo.Menu;
import com.storyxc.pojo.QueryPageBean;

import java.util.List;

/**
 * @author Xc
 * @description
 * @createdTime 2020/1/6 15:50
 */
public interface MenuService {

    PageInfo<Menu> findPage(QueryPageBean queryPageBean);

    void addMenu(Menu menu);

    Menu getMenuById(Integer id);

    void editMenu(Menu menu);

    void deleteMenu(Integer id);

    List<Menu> getMenusByUsername(String username);

    List<Menu> getAllMenu();

    List<Menu> getAllMenus();
}
