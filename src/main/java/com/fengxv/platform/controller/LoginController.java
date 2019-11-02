package com.fengxv.platform.controller;

import com.alibaba.fastjson.JSONObject;
import com.fengxv.platform.entity.User;
import com.fengxv.platform.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("api/v1")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 跳转到登陆页面
     * @return
     */
    @RequestMapping("login")
    @ApiOperation(nickname = "userLogin",value="跳转到登录页",httpMethod = "GET")
    public String userLogin(){
        return "login";
    }


    /**
     * 跳转到注册页面
     * @return
     */
    @RequestMapping("register")
    @ApiOperation(nickname = "register",value="跳转到登录页",httpMethod = "GET")
    public String register(){
        return "register";
    }

    /**
     * 用户校验
     * @param username  用户名
     * @param password  用户密码
     * @return
     */
    @RequestMapping(value = {"loginVerify"})
    @ApiOperation(nickname = "loginVerify",value="用户校验",httpMethod = "POST")
    public ModelAndView loginVerify(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           ModelAndView modelAndView) {
        JSONObject jsonObject = userService.userVerify(username,password);
        if((Integer) jsonObject.get("code") == 410){//未注册
            modelAndView.setViewName("login");
            modelAndView.addObject("error",jsonObject.get("msg"));
        }else if((Integer) jsonObject.get("code") == 411){//密码错误
            modelAndView.setViewName("login");
            modelAndView.addObject("error",jsonObject.get("msg"));
        }else {
            modelAndView.setViewName("homePage");
            modelAndView.addObject("userName",username);
        }
        return  modelAndView;

    }

}
