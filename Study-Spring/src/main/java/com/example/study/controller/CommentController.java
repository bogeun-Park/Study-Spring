package com.example.study.controller;

import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.example.study.service.CommentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CommentController {
	private final CommentService commentService;
	
	@PostMapping("/comment_process")
	public String postComment(@RequestParam Map<String, String> formData, Authentication auth) {
		Long id = commentService.saveComment(formData, auth);
		
		return "redirect:/list/read/" + id;
	}
}
