package com.fengxv.platform.controller;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;


/**
 *
 * @author wangfei
 * qq第三方登录
 */
@Controller
public class QQController {
    public static Logger logger = LoggerFactory.getLogger(QQController.class);

    @RequestMapping(value="/")
    public String root(HttpServletRequest request){
//        String result = "<!DOCTYPE html><html><head><meta charset='UTF-8'><title>qq login</title></head><body><a href='http://www.fengxv.com/authorizeUrl'>qq第三方登陆</a><br><a href='http://wangfeifei.free.ngrok.cc/weixinlogin'>微信第三方登陆</a></body></html>";
//        return result;
        return "index";
    }


    /**
     * 重定向到qq授权页面
     * @param request
     * @return
     * @throws QQConnectException
     */
    @RequestMapping(value="/authorizeUrl")
    public String authorizeUrl(HttpServletRequest request)throws QQConnectException{
        String authorizeUrl = new Oauth().getAuthorizeURL(request);
        return "redirect:"+authorizeUrl;
    }

    /**
     * qq授权回调路径
     * @param request
     * @return
     * @throws QQConnectException
     */
    @RequestMapping(value="/scratch",method=RequestMethod.GET)
    @ResponseBody
    public String qqLoginCallback(HttpServletRequest request) throws QQConnectException{
        //通过回调中的code得到accessToken
        AccessToken accessTokenObj = new Oauth().getAccessTokenByRequest(request);
        String accessToken = accessTokenObj.getAccessToken();
        if(accessToken == null || "".equals(accessToken)){
            throw new QQConnectException("accessToken为空，授权失败");
        }
        //通过accessToken得到openId
        OpenID openIdObj = new OpenID(accessToken);
        if(openIdObj == null || "".equals(openIdObj.getUserOpenID())){
            throw new QQConnectException("openIdObj为空，授权失败");
        }
        String openId = openIdObj.getUserOpenID();
        logger.info("用户唯一的openId: "+openId);

        //通过accessToken和openId得到用户信息
        UserInfo qzoneUserInfo = new UserInfo(accessToken, openId);
        UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
        if(userInfoBean == null || userInfoBean.getRet() != 0){
            throw new QQConnectException(userInfoBean.getMsg()+",授权失败");
        }
        //得到用户昵称
        String nickname = userInfoBean.getNickname();
        String imgUrl30 = userInfoBean.getAvatar().getAvatarURL30();
        String imgUrl50 = userInfoBean.getAvatar().getAvatarURL50();
        String imgUrl100 = userInfoBean.getAvatar().getAvatarURL100();
        String result = "<!DOCTYPE html><html><head><meta charset='UTF-8'><title>qq login</title></head><body>qq昵称："+nickname+"<br>qq头像:<img src='"+imgUrl30+"'/>" +
                "" +
                "<br>qq头像:<img src='"+imgUrl30+"'/>" +
                "<br>qq头像:<img src='"+imgUrl100+"'/></body></html>";
        return result;
    }


}

