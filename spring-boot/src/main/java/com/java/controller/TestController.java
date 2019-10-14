package com.java.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/getMsg.action")
    public String getMsg() {
        System.out.println("000");
        return "hello ~  ppp";
    }
}
