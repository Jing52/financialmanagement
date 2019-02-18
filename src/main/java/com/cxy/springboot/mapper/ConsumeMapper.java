package com.cxy.springboot.mapper;

import com.cxy.springboot.entity.Consume;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Mapper
public interface ConsumeMapper {
    int insert(Consume record);

    int insertSelective(Consume record);

    List<Consume> selectConsumeWithPage(Map<String,Object> map);

    int selectConsumeWithCount(Map<String,Object> map);

    String getMaxSum(@Param("userId") String userId, @Param("startTime") String startTime, @Param("endTime") String endTime);

    String getMaxDate(@Param("userId") String userId, @Param("startTime") String startTime, @Param("endTime") String endTime);

    int deleteConsume(String consumeId);
}