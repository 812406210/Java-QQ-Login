package com.fengxv.platform.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fengxv.platform.entity.User;

public interface UserDao extends BaseMapper<User> {
    int deleteByPrimaryKey(Integer id);

    Integer insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}