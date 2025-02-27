package com.example.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BasicController {	
	@GetMapping("/")
    public String index(Model model) {
        model.addAttribute("name", "홍길동");
        return "index";
    }
	
    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello";
    }
}
