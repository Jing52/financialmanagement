package com.cxy.springboot.controller;

import com.cxy.springboot.entity.UserInfo;
import com.cxy.springboot.service.UserInfoService;
import com.cxy.springboot.utils.GlobalConst;
import com.cxy.springboot.utils.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Auther: cxy
 * @Date: 2019/1/7
 * @Description: 登陆
 */
@Controller
@RequestMapping("/")
public class LoginController {
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/login")
    public String login(Model model){
        return "/login/login";
    }

    /**
     * 通过系统账号登录系统
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/loginSys",method=RequestMethod.POST)
    @ResponseBody
    @Transactional
    public String loginSys(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
//		String token = request.getParameter("token");
//		String code = request.getParameter("code");
        password = password.toUpperCase();
        try {
            UserInfo userInfo = userInfoService.selectUserInfoByCondition(userId,password);
            if(userInfo==null){
                return ResponseCode.QUERY_ERROR.getCode();
            }else{
                session.setAttribute(GlobalConst.USER_SESSION_KEY,userInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseCode.SUCCESS.getCode();
    }
}
