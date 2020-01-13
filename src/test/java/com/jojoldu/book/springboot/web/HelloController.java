package com.jojoldu.book.springboot.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "Hello";
    }

    @GetMapping("/hello/dto")
    public String helloDto(@RequestParam("email") String email, @RequestParam("password") String password){

        if(email.equals("area409@naver.com") && password.equals("persona33"))
            return "{\"result\" : \"success\"}";
        else
            return "{\"result\" : \"fail\"}";
    }
}
