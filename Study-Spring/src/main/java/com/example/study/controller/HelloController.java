package com.example.study.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	@GetMapping("/")
    public String index() {
        return "Hello, Spring Boot!";
    }
	
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
    
    @GetMapping("test")
    public String sayHello() {
        return "테스트페이지 입니다!";
    }
}
