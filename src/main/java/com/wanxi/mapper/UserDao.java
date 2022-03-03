package com.wanxi.mapper;


import com.wanxi.entity.User;
import com.wanxi.entity.UserEs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserDao extends BaseDao<User> {
     User findByUsernameAndPassword(User user);
     int addText(User user);

     @Select(" select * from member_list left join files ON member_list.id=files.user_id left join text on\n" +
             "        member_list.id=text.user_id")
     List<UserEs> selectAll();
}
