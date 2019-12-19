package com.storyxc.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Xc
 * @description
 * @createdTime 2019/12/19 13:42
 */
@Data
public class Menu implements Serializable {
    private Integer id;
    private String name;
    private String linkUrl;
    private String path;
    private Integer priority;
    private String description;
    private String icon;
    private Set<Role> roles = new HashSet<Role>(0);
    private List<Menu> children = new ArrayList<>();
    private Integer parentMenuId;
}
