package com.xiaoshu.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaoshu.admin.entity.Role;
import com.xiaoshu.admin.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;
import java.util.Set;


public interface UserMapper extends BaseMapper<User> {

    User selectUserByMap(Map<String,Object> map);

    void saveUserRoles(@Param("userId")String id, @Param("roleIds")Set<Role> roles);

    void dropUserRolesByUserId(@Param("userId")String id);
    
    @Select("select imgPath from sys_user where id=#{id}")
    String imgPath(User user);
}