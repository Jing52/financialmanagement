package com.cxy.springboot.controller;

import com.cxy.springboot.entity.UserInfo;
import com.cxy.springboot.service.UserInfoService;
import com.cxy.springboot.entity.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: cxy
 * @Date: 2019/1/9
 * @Description:
 */
@Controller
@RequestMapping("/info")
public class PersonalInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("toInfo")
    public String toInfo(Model model){
        return "/info/info";
    }

    @RequestMapping("selectUserInfo")
    @ResponseBody
    public Object selectUserInfo(HttpServletRequest request, HttpServletResponse response){
        String userName = request.getParameter("userName");
        String msisdn = request.getParameter("msisdn");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String isAdmin = request.getParameter("isAdmin");
        String page = request.getParameter("page");
        String pageSize = request.getParameter("rows");

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("userName",userName);
        map.put("msisdn",msisdn);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        map.put("isAdmin",isAdmin);
        map.put("page",page);
        map.put("pageSize",pageSize);

        Map<String, Object> retMap = userInfoService.selectUserInfo(map);
        return retMap;
    }

    @RequestMapping("userInfoAdd")
    public String userInfoAdd(Model model){
        return "/info/info_add";
    }

    @RequestMapping("addUserInfo")
    @ResponseBody
    public Object addUserInfo(HttpServletRequest request,HttpServletResponse response){
        Map<String, Object> map = new HashMap<String,Object>();
        String userId = request.getParameter("userId");
        String userName = request.getParameter("userName");
        String msisdn = request.getParameter("msisdn");
        String password = request.getParameter("password");
        String isAdmin = request.getParameter("isAdmin");
        String weixin = request.getParameter("weixin");
        String qq = request.getParameter("qq");
        String userLogo = request.getParameter("userLogo");

        UserInfo userInfo = new UserInfo();

        userInfo.setUserName(userName);
        userInfo.setUserId(userId);
        userInfo.setPassword(password);
        userInfo.setMsisdn(msisdn);
        userInfo.setIsAdmin(Integer.valueOf(isAdmin));
        userInfo.setWeixin(weixin);
        userInfo.setQq(qq);
        userInfo.setUserLogo(userLogo);

        int result = userInfoService.insert(userInfo);
        return result;
    }

    @RequestMapping("deleteUserInfo")
    @ResponseBody
    public int deleteUserInfo(HttpServletRequest request, HttpServletResponse response){
        String userId = request.getParameter("userId");
        int result = userInfoService.deleteUserInfo(userId);
        return result;
    }
}
