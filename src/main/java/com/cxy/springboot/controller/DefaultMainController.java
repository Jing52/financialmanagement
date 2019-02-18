package com.cxy.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther: cxy
 * @Date: 2019/1/14
 * @Description:
 */
@Controller
@RequestMapping("/default")
public class DefaultMainController {

    @RequestMapping("/toDefaultMain")
    public String toDefaultMain(Model model){
        return "/default_main";
    }
}
