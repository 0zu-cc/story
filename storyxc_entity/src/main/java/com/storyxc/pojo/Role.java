package com.storyxc.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Xc
 * @description
 * @createdTime 2019/12/19 13:40
 */
@Data
public class Role implements Serializable {
    private Integer id;
    private String name; // 角色名称
    private String keyword; // 角色关键字，用于权限控制
    private String description; // 描述
    private Set<User> users = new HashSet<User>(0);
    private Set<Permission> permissions = new HashSet<Permission>(0);
    private List<Integer> permissionNums; // 权限ID集合
    private LinkedHashSet<Menu> menus = new LinkedHashSet<Menu>(0);
    private List<Integer> menuNums; // 菜单ID集合
}
