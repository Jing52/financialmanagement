package com.cxy.springboot.service;

import com.alibaba.fastjson.JSON;
import com.cxy.springboot.entity.UserInfo;
import com.cxy.springboot.mapper.UserInfoMapper;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: cxy
 * @Date: 2019/1/8
 * @Description:
 */
@Service
public class UserInfoService{
    private final static Logger logger = LoggerFactory.getLogger(UserInfoService.class);
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Transactional
    public UserInfo selectUserInfoByCondition(String userId, String password) {
        try {
            UserInfo userInfo = userInfoMapper.selectUserInfoByCondition(userId,password);
            if(userInfo!=null){
                logger.info("登陆用户信息：" + userInfo.toString());
                return userInfo;
            }
        }catch(Exception e){
            e.printStackTrace();
            logger.info("登陆异常："+e.toString());
        }
        return null;
    }

    public Map<String, Object> selectUserInfo(Map<String,Object> map){
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        int currentPage = Integer.valueOf(map.get("page").toString());
        int pageSize = Integer.valueOf(map.get("pageSize").toString());
        PageHelper.startPage(currentPage, pageSize);

        List<UserInfo> allItems = null;
        int countNums = 0;
        try{
            logger.info("展示全部用户信息入参：" + JSON.toJSONString(map));
            allItems = userInfoMapper.selectUserInfoWithPage(map);        //全部用户信息
            logger.info("展示全部用户信息出参：" + JSON.toJSONString(allItems));
            countNums = userInfoMapper.selectUserInfoWithCount(map);           //总记录数
            logger.info("展示全部用户信息总记录数：" + countNums);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("展示异常："+e.toString());
        }
//        PageBean<UserInfo> pageData = new PageBean<>(currentPage, pageSize, countNums);

        Map<String, Object> resultMap = new HashMap<>();
        //总条数
        resultMap.put("total", countNums);
        //数据
        resultMap.put("rows", allItems);
        return resultMap;
    }

    public int insert(UserInfo userInfo){
        int result = 0;
        try{
            logger.info("新增用户信息入参：" + JSON.toJSONString(userInfo));
            result = userInfoMapper.insert(userInfo);
            logger.info("新增用户信息出参：" + result);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("新增用户信息异常：" + e.toString());
        }
        return result;
    }

    public int deleteUserInfo(String userId){
        int result = 0;
        try{
            logger.info("删除用户入参：" + userId);
            result = userInfoMapper.deleteUserInfoByUserId(userId);
            logger.info("删除用户出参：" + result);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("删除用户异常：" + e.toString());
        }
        return result;
    }
}
