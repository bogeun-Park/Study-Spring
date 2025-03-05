package com.example.study.controller;

import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.study.security.CustomUser;
import com.example.study.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;
	
	@GetMapping("/login")
    public String login() {

        return "loginCategory/login";
    }
	
	@GetMapping("/login/register")
    public String register() {
        
        return "loginCategory/register";
    }
	
	@PostMapping("/login/register_process")
    public String registerProcess(@RequestParam Map<String, String> formData) {
		memberService.saveMember(formData);

        return "redirect:/list";
    }
	
	@GetMapping("/myPage")
    public String myPage(Model model, Authentication auth) {
		CustomUser user = (CustomUser) auth.getPrincipal();
		
		model.addAttribute("user", user);
	
        return "loginCategory/myPage";
    }
}
