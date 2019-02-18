package com.cxy.springboot.controller;

import com.cxy.springboot.service.ConsumeHistoryService;
import com.cxy.springboot.utils.DateUtils;
import com.cxy.springboot.utils.GlobalConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: cxy
 * @Date: 2019/1/15
 * @Description: 消费记录
 */
@Controller
@RequestMapping("/history")
public class ConsumeHistoryController {

    @Autowired
    private ConsumeHistoryService consumeHistoryService;

    @RequestMapping("/toConsume")
    public String toConsume(HttpSession session, Model model){
        session.setAttribute(GlobalConst.CONSUME_TYPE_KEY,GlobalConst.CONSUME_TYPE_VALUE);
        return "/consume/consume";
    }

    /**
     * 消费记录
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/selectConsume")
    @ResponseBody
    public Object selectConsume(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> retMap = new HashMap<String,Object>();

        String userId = request.getParameter("userId");
        if(userId.isEmpty()){
            retMap.put("error_key","0");
            retMap.put("error_value","无法获取用户名！");
            return retMap;
        }
        String consumeType = request.getParameter("consumeType");
        String nation = request.getParameter("nation");
        String provinceId = request.getParameter("provinceId");
        String cityId = request.getParameter("cityId");
        String minSum = request.getParameter("minSum");
        String maxSum = request.getParameter("maxSum");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String page = request.getParameter("page");
        String pageSize = request.getParameter("rows");

        String year = DateUtils.getCurrentYear();
        if(startTime.isEmpty()){
            startTime = year+"-01-01 00:00:00";
        }else{
            startTime = startTime+" 00:00:00";
        }
        if(endTime.isEmpty()){
            endTime = year+"-12-31 23:59:59";
        }else {
            endTime = endTime+" 23:59:59";
        }

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("userId",userId);
        map.put("consumeType",consumeType);
        map.put("nation",nation);
        map.put("provinceId",provinceId);
        map.put("cityId",cityId);
        map.put("minSum",minSum);
        map.put("maxSum",maxSum);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        map.put("page",page);
        map.put("pageSize",pageSize);

        retMap = consumeHistoryService.selectConsume(map);
        return retMap;
    }

    /**
     * 最高消费
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getMaxSum")
    @ResponseBody
    public Object getMaxSum(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> retMap = new HashMap<String,Object>();

        String userId = request.getParameter("userId");
        if(userId.isEmpty()){
            retMap.put("error_key","0");
            retMap.put("error_value","无法获取用户名！");
            return retMap;
        }

        String year = DateUtils.getCurrentYear();
        String startTime = year+"-01-01 00:00:00";
        String endTime = year+"-12-31 23:59:59";

        retMap = consumeHistoryService.getMaxSum(userId,startTime,endTime);
        return retMap;
    }

    /**
     * 获取消费最多的月份
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getMaxDate")
    @ResponseBody
    public Object getMaxDate(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> retMap = new HashMap<String,Object>();

        String userId = request.getParameter("userId");
        if(userId.isEmpty()){
            retMap.put("error_key","0");
            retMap.put("error_value","无法获取用户名！");
            return retMap;
        }

        String year = DateUtils.getCurrentYear();
        String startTime = year+"-01-01 00:00:00";
        String endTime = year+"-12-31 23:59:59";

        retMap = consumeHistoryService.getMaxDate(userId,startTime,endTime);
        return retMap;
    }

    /**
     * 删除消费记录
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/deleteConsume")
    @ResponseBody
    public Object deleteConsume(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> retMap = new HashMap<String,Object>();
        String consumeId = request.getParameter("consumeId");
        int result = consumeHistoryService.deleteConsume(consumeId);
        return result;
    }

    /**
     * 跳转消费记录新增页面
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/toConsumeAdd")
    public String toConsumeAdd(HttpSession session, Model model){
//        session.setAttribute(GlobalConst.CONSUME_TYPE_KEY,GlobalConst.CONSUME_TYPE_VALUE);
        return "/consume/consume_add";
    }
}
