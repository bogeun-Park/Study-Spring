package com.example.study.controller;

import java.util.List;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.study.domain.Notice;
import com.example.study.repository.NoticeRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class NoticeController {
	private final NoticeRepository noticeRepository;

	@GetMapping("/notice")
    public String list(Model model) {
		List<Notice> notices = noticeRepository.findAll(Sort.by(Sort.Order.asc("id")));
		
        model.addAttribute("notices", notices);
        return "notice";
    }
}
