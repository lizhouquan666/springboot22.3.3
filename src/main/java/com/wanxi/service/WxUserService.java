package com.wanxi.service;

import com.wanxi.entity.LoginParams;

import com.wanxi.entity.WxPermission;
import com.wanxi.entity.WxUser;

import java.util.List;

public interface WxUserService {

    WxUser getUserByName(String name);

    List<WxPermission> getPermissionsByUserId(Integer id);

    String login(LoginParams loginParams);

}