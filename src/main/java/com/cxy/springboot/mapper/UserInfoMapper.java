package com.cxy.springboot.mapper;

import com.cxy.springboot.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserInfoMapper {
    int insert(UserInfo userInfo);

    UserInfo selectUserInfoByCondition(@Param("userId") String var1, @Param("password") String var2);

    List<UserInfo> selectUserInfoWithPage(Map<String,Object> map);

    int selectUserInfoWithCount(Map<String,Object> map);

    int deleteUserInfoByUserId(String userId);
}