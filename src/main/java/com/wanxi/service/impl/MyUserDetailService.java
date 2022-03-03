package com.wanxi.service.impl;

import com.wanxi.entity.WxPermission;
import com.wanxi.entity.WxUser;
import com.wanxi.service.WxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;


@Service("userDetailsService")
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    WxUserService wxUserService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        WxUser user= wxUserService.getUserByName(username);
        List<WxPermission> permissionList= wxUserService.getPermissionsByUserId(user.getId());
        HashSet<WxPermission> permissions = new HashSet<>(permissionList);
        user.setAuthorities(permissions);
        return user;
    }
}