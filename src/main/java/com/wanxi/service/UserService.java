package com.wanxi.service;


import com.wanxi.entity.User;
import com.wanxi.commonresult.CommonResult;

public interface UserService extends BaseService<User>{

    CommonResult<String> login(User user);
    CommonResult enable(User user);

    CommonResult addText(User user);
}
