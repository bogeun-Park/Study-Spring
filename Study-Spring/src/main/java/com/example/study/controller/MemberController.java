package com.example.study.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import com.example.study.domain.Member;
import com.example.study.dto.MemberDto;
import com.example.study.security.CustomUser;
import com.example.study.security.Jwtutil;
import com.example.study.service.MemberService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	
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
	
	@GetMapping("/user/{id}")
	@ResponseBody
    public MemberDto getUSer(@PathVariable("id") Long id, Model model, Authentication auth) {
		Optional<Member> member = memberService.getIdItem(id);
		
		if(member.isPresent()) {
			return memberService.getMemberDto(member.get());
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID " + id + " not found");
		} 
    }
	
	@PostMapping("/login/jwt")
	@ResponseBody
	public String loginJwt(@RequestBody Map<String, String> formData, HttpServletResponse response) {
		var authToken = new UsernamePasswordAuthenticationToken(formData.get("username"), formData.get("password"));
		Authentication auth = authenticationManagerBuilder.getObject().authenticate(authToken);
		SecurityContextHolder.getContext().setAuthentication(auth);		
		
		String jwt = Jwtutil.createToken(SecurityContextHolder.getContext().getAuthentication());
		
		Cookie cookie = new Cookie("jwt", jwt);
		cookie.setMaxAge(10);
		cookie.setHttpOnly(true);
		cookie.setPath("/");
		response.addCookie(cookie);
		
		return jwt;
	}
	
	@GetMapping("/my-page/jwt")
	@ResponseBody
	public String mypageJWT(Authentication auth) {
		CustomUser user = (CustomUser) auth.getPrincipal();
		
		System.out.println(auth);
		System.out.println(user);
		
		return "마이페이지데이터";
	}
}
