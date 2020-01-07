package com.storyxc.mapper;

import com.storyxc.pojo.Menu;

import java.util.List;

/**
 * @author Xc
 * @description
 * @createdTime 2020/1/6 15:51
 */
public interface MenuDao {
    List<Menu> findPage(String queryString);

    void addMenu(Menu menu);

    Menu getMenuById(Integer id);

    void editMenu(Menu menu);

    void deleteMenu(Integer id);

    List<Menu> getAllMenus();

    List<Menu> queryAllMenusByUsername(String username);

    void deleteMenuAndRole(Integer id);
}
