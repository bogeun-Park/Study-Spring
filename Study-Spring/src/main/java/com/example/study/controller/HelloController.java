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
    public String sayHello() {
        return "hello";
    }
}
