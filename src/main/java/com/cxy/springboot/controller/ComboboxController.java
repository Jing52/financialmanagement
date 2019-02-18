package com.cxy.springboot.controller;

import com.cxy.springboot.entity.ConsumeType;
import com.cxy.springboot.entity.Region;
import com.cxy.springboot.service.ComboboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: cxy
 * @Date: 2019/1/15
 * @Description: 下拉框
 */
@Controller
@RequestMapping("/select")
public class ComboboxController {
    @Autowired
    private ComboboxService comboboxService;

    /**
     * 消费类型
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/type")
    @ResponseBody
    public Object selectConsumeType(HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> map = new HashMap<String,Object>();
        List<ConsumeType> list = null;
        try{
            list = comboboxService.selectAllType();
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 国家
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/nation")
    @ResponseBody
    public Object selectNation(HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> map = new HashMap<String,Object>();
        List<Region> list =new ArrayList<Region>();
        try{
            list = comboboxService.selectAllRegion(request.getParameter("world").toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 省份
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/province")
    @ResponseBody
    public Object selectProvince(HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> map = new HashMap<String,Object>();
        List<Region> list = null;
        String nation = request.getParameter("nation").toString();
        try{
            list = comboboxService.selectAllRegion(nation);
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 地市
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/city")
    @ResponseBody
    public Object selectCity(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> map = new HashMap<String,Object>();
        List<Region> list = null;
        String province = request.getParameter("province").toString();
        try{
            list = comboboxService.selectAllRegion(province);
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
