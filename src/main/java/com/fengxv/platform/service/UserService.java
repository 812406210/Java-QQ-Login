package com.fengxv.platform.service;

import com.fengxv.platform.entity.User;

public interface UserService {
    public User getUserById(int userId);

    boolean addUser(User record);



}
