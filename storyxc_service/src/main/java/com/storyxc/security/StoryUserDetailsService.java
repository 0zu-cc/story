package com.storyxc.security;

import com.storyxc.pojo.Permission;
import com.storyxc.pojo.Role;
import com.storyxc.pojo.User;
import com.storyxc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Set;

/**
 * @author Xc
 * @description
 * @createdTime 2019/12/19 13:17
 */
@Component
public class StoryUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.queryUserByUsername(username);
        if (user == null) {
            return null;
        }
        //用户权限集合
        ArrayList<GrantedAuthority> authorityList = new ArrayList<>();
        for (Role role : user.getRoles()) {
            Set<Permission> permissions = role.getPermissions();
            if (permissions != null) {
                for (Permission permission : permissions) {
                    SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permission.getKeyword());
                    authorityList.add(authority);
                }
            }
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorityList);
    }
}
