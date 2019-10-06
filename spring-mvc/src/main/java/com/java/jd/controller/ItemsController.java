package com.java.jd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/items")
public class ItemsController {

    @ResponseBody
    @RequestMapping("/queryItems.action")
    public String queryItems() {

        return "java";
    }

}
