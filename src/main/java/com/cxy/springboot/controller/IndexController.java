package com.cxy.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther: cxy
 * @Date: 2019/1/7
 * @Description:
 */
@Controller
@RequestMapping("/index")
public class IndexController {
    @RequestMapping("/index")
    public String index(Model model){
        return "index/index";
    }
}
