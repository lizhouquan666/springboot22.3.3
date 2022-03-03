package com.wanxi.service.impl;
import com.wanxi.mapper.WxUserDao;
import com.wanxi.entity.LoginParams;
import com.wanxi.entity.WxPermission;
import com.wanxi.entity.WxUser;
import com.wanxi.service.WxUserService;
import com.wanxi.tool.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class WxUserServiceImpl implements WxUserService {

    @Autowired
    private WxUserDao wxUserDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public WxUser getUserByName(String name) {
        List<WxUser> users= wxUserDao.getUserByName(name);
        Assert.isTrue(users.size()==1,"您输入的账户不存在，或者有多个相同的账户");
        return users.get(0);
    }

    @Override
    public List<WxPermission> getPermissionsByUserId(Integer id) {
        return wxUserDao.getPermissionByUserId(id);
    }

    @Override
    public String login(LoginParams loginPrams) {
        String username = loginPrams.getUsername();
        Assert.notNull(username,"账号必须不能为空");
        String password = loginPrams.getPassword();
        Assert.notNull(password,"密码必须不能为空");
        WxUser userByName = getUserByName(username);
        boolean matches = passwordEncoder.matches(password, userByName.getPassword());
        if(matches){
            return jwtTokenUtil.generateToken(userByName);
        }
        return null;
    }
}