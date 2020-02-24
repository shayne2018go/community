package com.mawen.community.community.mapper;

import com.mawen.community.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    @Insert("insert into user (id, account_id, name, token, gmt_created, gmt_modified) values(#{id}, #{accountId}, #{name}, #{token}, #{gmtCreated}, #{gmtModified})")
    void insert(User user);
}