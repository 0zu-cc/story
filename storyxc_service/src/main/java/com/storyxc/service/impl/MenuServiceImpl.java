package com.storyxc.service.impl;

import com.github.pagehelper.PageInfo;
import com.storyxc.mapper.MenuDao;
import com.storyxc.pojo.Menu;
import com.storyxc.pojo.QueryPageBean;
import com.storyxc.service.MenuService;
import com.storyxc.util.PageHelperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author Xc
 * @description
 * @createdTime 2020/1/6 15:51
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;


    @Override
    public List<Menu> getMenusByUsername(String username) {
        //获取所有的菜单并封装层级关系
        List<Menu> allMenus = getAllMenus();
        //获取当前用户拥有的所有菜单
        List<Menu> userMenuList = menuDao.queryAllMenusByUsername(username);
        //过滤到当前用户没有的菜单 为避免并发修改异常 使用迭代器进行操作
        Iterator<Menu> firstLevelMenuIterator = allMenus.iterator();
        while (firstLevelMenuIterator.hasNext()) {
            Menu firstLevelMenu = firstLevelMenuIterator.next();
            if (firstLevelMenu != null) {
                if (firstLevelMenu.getChildren() != null && firstLevelMenu.getChildren().size() > 0) {
                    /*获取当前一级菜单的迭代器
                    Iterator<Menu> secondLevelMenuIterator = firstLevelMenu.getChildren().iterator();
                    while (secondLevelMenuIterator.hasNext()) {
                        Menu secondLevelMenu = secondLevelMenuIterator.next();
                        //如果用户菜单中不包含此二级菜单,从二级菜单的迭代器中移除
                        if (!userMenuList.contains(secondLevelMenu)) {
                            secondLevelMenuIterator.remove();
                        }
                    }*/
                    //如果用户菜单中不包含此二级菜单,从二级菜单的迭代器中移除
                    firstLevelMenu.getChildren().removeIf(secondLevelMenu -> !userMenuList.contains(secondLevelMenu));
                }

                //如果当前一级菜单下没有二级菜单,移除当前一级菜单
                if (firstLevelMenu.getChildren() == null || firstLevelMenu.getChildren().size() == 0) {
                    firstLevelMenuIterator.remove();
                }
            }
        }
        //菜单排序 按照优先级升序排列
        for (Menu menu : allMenus) {
            if(menu.getChildren()!=null&&menu.getChildren().size()>0){
                Collections.sort(menu.getChildren(), Comparator.comparingInt(Menu::getPriority));
            }
        }
        Collections.sort(allMenus, Comparator.comparingInt(Menu::getPriority));
        return allMenus;
    }

    @Override
    public List<Menu> getAllMenu() {
        return menuDao.getAllMenus();
    }

    @Override
    public List<Menu> getAllMenus() {
        List<Menu> menuList = menuDao.getAllMenus();
        //创建一个虚拟的顶级目录
        Menu rootMenu = new Menu();
        rootMenu.setChildren(new ArrayList<>());
        //创建菜单id和菜单对象的映射map
        Map<Integer, Menu> menuMap = new HashMap<>();
        for (Menu menu : menuList) {
            menuMap.put(menu.getId(), menu);
        }
        menuMap.put(0, rootMenu);
        for (Menu menu : menuList) {
            //将一级目录封装到虚拟顶级目录
            if (menu.getParentMenuId() == null) {
                rootMenu.getChildren().add(menu);
                continue;
            }
            //封装二级目录
            Menu parentMenu = menuMap.get(menu.getParentMenuId());
            if (parentMenu != null) {
                if (parentMenu.getChildren() == null) {
                    parentMenu.setChildren(new ArrayList<>());
                }
                parentMenu.getChildren().add(menu);
            }
        }
        return rootMenu.getChildren();
    }

    @Override
    public PageInfo<Menu> findPage(QueryPageBean queryPageBean) {
        PageHelperUtils.startPage(queryPageBean);
        List<Menu> menuList = menuDao.findPage(queryPageBean.getQueryString());
        return new PageInfo<>(menuList);
    }

    @Override
    public void addMenu(Menu menu) {
        menuDao.addMenu(menu);
    }

    @Override
    public Menu getMenuById(Integer id) {
        return menuDao.getMenuById(id);
    }

    @Override
    public void editMenu(Menu menu) {
        menuDao.editMenu(menu);
    }

    @Override
    @Transactional
    public void deleteMenu(Integer id) {
        menuDao.deleteMenu(id);
        menuDao.deleteMenuAndRole(id);
    }

}
