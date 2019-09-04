package com.fengxv.platform;

import com.fengxv.platform.dao.UserDao;
import com.fengxv.platform.entity.User;
import com.fengxv.platform.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {
    @Autowired
    UserService userService;

    @Resource
    UserDao userDao;
    /**
     * 用户测试---mybatisPlus
     */
    @Test
    public void testUser(){
        User user = userService.getUserById(1);
        System.out.println(user);
    }

    /**
     * 用户测试---mybatis
     */
    @Test
    public void testUser1(){
        User user = new User();
        user.setUserName("min");
        int num = userDao.insert(user);
        System.out.println(num);
    }

}
