package com.fengxv.platform.service.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.fengxv.platform.dao.UserDao;
import com.fengxv.platform.entity.User;
import com.fengxv.platform.service.UserService;
import com.fengxv.platform.utils.CommonCode;
import com.fengxv.platform.utils.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public User getUserById(int userId) {
        return userDao.selectByPrimaryKey(userId);
    }

    @Override
    public boolean addUser(User record){
        boolean result = false;
        try {
            userDao.insertSelective(record);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Page selectAll(Integer pageNum ,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList =  userDao.selectList(null);
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return  new Page(pageNum, pageSize, pageInfo.getTotal(), pageInfo.getPages(),userList);
    }

    @Override
    public JSONObject userVerify(String userName, String password) {
        JSONObject returnJson = new JSONObject();
        User user = new User();
        user.setUserName(userName);
        User userByName = userDao.selectOne(user);
        if(userByName == null){
            returnJson.put("msg",CommonCode.USER_UNREGISTER.getMessage());
            returnJson.put("code",CommonCode.USER_UNREGISTER.getCode());
            return returnJson;
        }
        if(userByName.getPassword().equals(password)){
            returnJson.put("msg",CommonCode.SUCCESS.getMessage());
            returnJson.put("code",CommonCode.SUCCESS.getCode());
        }else {
            returnJson.put("msg",CommonCode.PASSWORD_ERROR.getMessage());
            returnJson.put("code",CommonCode.PASSWORD_ERROR.getCode());
        }
        return returnJson;
    }

}