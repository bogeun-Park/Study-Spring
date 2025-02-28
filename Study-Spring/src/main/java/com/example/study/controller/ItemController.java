package com.example.study.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.study.domain.Item;
import com.example.study.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ItemController {
	private final ItemRepository itemRepository;

	@GetMapping("/list")
    public String list(Model model) {
		List<Item> items = itemRepository.findAll();  // itemRepository테이블의 모든 행을 리스트형태로 반환
		
        model.addAttribute("items", items);  // (html 파일의 변수명, 전달할 데이터)
        return "list";
    }
}
