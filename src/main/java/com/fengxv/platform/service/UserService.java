package com.fengxv.platform.service;

import com.alibaba.fastjson.JSONObject;
import com.fengxv.platform.entity.User;
import com.fengxv.platform.utils.Page;


public interface UserService {
    User getUserById(int userId);

    boolean addUser(User record);

    Page selectAll(Integer pageNum , Integer pageSize);

    JSONObject userVerify(String userName, String password);




}
