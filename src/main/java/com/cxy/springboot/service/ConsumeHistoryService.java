package com.cxy.springboot.service;

import com.alibaba.fastjson.JSON;
import com.cxy.springboot.entity.Consume;
import com.cxy.springboot.mapper.ConsumeMapper;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: cxy
 * @Date: 2019/1/17
 * @Description:
 */
@Service
public class ConsumeHistoryService {
    private final static Logger logger = LoggerFactory.getLogger(UserInfoService.class);

    @Autowired
    private ConsumeMapper consumeMapper;

    /**
     * 消费记录
     * @param map
     * @return
     */
    public Map<String,Object> selectConsume(Map<String,Object> map) {
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        int currentPage = Integer.valueOf(map.get("page").toString());
        int pageSize = Integer.valueOf(map.get("pageSize").toString());
        PageHelper.startPage(currentPage, pageSize);

        List<Consume> consumeList = null;
        int countNums = 0;
        try{
            logger.info("展示全部消费记录入参：" + JSON.toJSONString(map));
            consumeList = consumeMapper.selectConsumeWithPage(map);        //全部用户信息
            logger.info("展示全部消费记录出参：" + JSON.toJSONString(consumeList));
            countNums = consumeMapper.selectConsumeWithCount(map);           //总记录数
            logger.info("展示全部消费记录总记录数：" + countNums);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("展示异常："+e.toString());
        }

        Map<String, Object> resultMap = new HashMap<>();
        //总条数
        resultMap.put("total", countNums);
        //数据
        resultMap.put("rows", consumeList);
        return resultMap;
    }

    /**
     * 获取最高消费
     * @param userId
     * @return
     */
    public Map<String,Object> getMaxSum(String userId,String startTime,String endTime) {
        Map<String, Object> resultMap = new HashMap<>();
        try{
            String maxSum = consumeMapper.getMaxSum(userId,startTime,endTime);
            logger.info("最高消费出参：" + maxSum);
            resultMap.put("maxSum", maxSum);
        }catch(Exception e){
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * 当年最高消费月份
     * @param userId
     * @param startTime
     * @param endTime
     * @return
     */
    public Map<String,Object> getMaxDate(String userId, String startTime, String endTime) {
        Map<String, Object> resultMap = new HashMap<>();
        try{
            String maxSum = consumeMapper.getMaxDate(userId,startTime,endTime);
            logger.info("最高消费月份出参：" + maxSum);
            resultMap.put("maxSum", maxSum+"月份");
        }catch(Exception e){
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * 删除消费记录
     * @param consumeId
     * @return
     */
    public int deleteConsume(String consumeId) {
        int result = 0;
        try{
            logger.info("删除消费记录入参：" + consumeId);
            result = consumeMapper.deleteConsume(consumeId);
            logger.info("删除消费记录出参：" + result);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("删除消费记录异常：" + e.toString());
        }
        return result;
    }
}
