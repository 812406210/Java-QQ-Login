package com.fengxv.platform.controller;

import com.fengxv.platform.entity.User;
import com.fengxv.platform.service.UserService;
import com.fengxv.platform.utils.ActionHelper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;


@RestController
@RequestMapping("api/v1")
public class UserController {

    public static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value="registerUser")
    @ApiOperation(nickname = "registerUser",value="添加用户",httpMethod = "POST")
    public ModelAndView registerUser(@ModelAttribute(value="user") User user,
                                     ModelAndView modelAndView ){
        userService.addUser(user);
        modelAndView.setViewName("homePage");
        modelAndView.addObject("userName",user.getUserName());
        return modelAndView;
    }

    @RequestMapping(value="updateUser")
    @ApiOperation(nickname = "updateUser",value="修改用户",httpMethod = "POST")
    public Map updateUser(@ApiParam(required = true, value = "用户") @RequestBody User user){
        User oldUser = userService.getUserById(user.getId());
        if(oldUser==null){
            throw  new RuntimeException("改用户不存在，无法修改");
        }
        BeanUtils.copyProperties(user,oldUser);
        userService.addUser(oldUser);
        return ActionHelper.responseOk();
    }

    @RequestMapping(value="selectAllUser")
    @ApiOperation(nickname = "selectAllUser",value="查询所有用户",httpMethod = "GET")
    public Map selectAllUser(@ApiParam(required = true, value = "当前页码") @RequestParam(required = false) Integer pageNum,
                             @ApiParam(required = true, value = "每页数量") @RequestParam(required = false) Integer pageSize){
        return ActionHelper.responseOk(userService.selectAll(pageNum,pageSize));
    }

}
