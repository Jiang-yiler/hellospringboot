package com.example.hellospringboot.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {
    @RequestMapping(value = "/hello1", method = RequestMethod.GET)
    public String getHello1(String name, String age) {
        return "name:" + name + "age" + age;
    }

    // 使用@RequestParam将前端传递来的name映射为getHello2的name1使用
    @RequestMapping(value = "/hello2", method = RequestMethod.GET)
    public String getHello2(@RequestParam(value = "name", required = false) String name1, @RequestParam(value = "age", required = false) String age1) {
        return "name:" + name1 + "age" + age1;
    }
    // Content-Type: application/x-www-form-urlencoded
    @RequestMapping(value = "/hello3", method = RequestMethod.POST)
    public String postHello1(String name, String age) {
        return "POST请求" + "name:" + name + "age" + age;
    }
    // TODO 使用@RequestBody从Content-Type: application/json获取参数
}
