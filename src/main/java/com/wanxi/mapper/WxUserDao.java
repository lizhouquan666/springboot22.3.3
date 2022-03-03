package com.wanxi.mapper;


import com.wanxi.entity.WxPermission;
import com.wanxi.entity.WxUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface WxUserDao {
    List<WxUser> getUserByName(String name);

    List<WxPermission> getPermissionByUserId(Integer userId);
}