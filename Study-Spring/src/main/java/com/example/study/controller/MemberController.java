package com.example.study.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.study.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;
	
	@GetMapping("/login/register")
    public String register(Model model) {
        
        return "loginCategory/register";
    }
	
	@PostMapping("/login/register_process")
    public String createProcess(@RequestParam Map<String, String> formData) {
		memberService.saveMember(formData);

        return "redirect:/list";
    }
}
