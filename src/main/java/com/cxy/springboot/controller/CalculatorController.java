package com.cxy.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther: cxy
 * @Date: 2019/1/23
 * @Description:
 */
@Controller
@RequestMapping("calculator")
public class CalculatorController {

    @RequestMapping("toCalcu")
    public String toCalcu(Model model){
        return "/calculator/calculator";
    }
}
