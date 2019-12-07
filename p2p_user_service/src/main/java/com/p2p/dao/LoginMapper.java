package com.p2p.dao;

import com.p2p.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {
    @Select("select * from  t_user where user_username=#{username}")
    User login(@Param("username") String username);
}
