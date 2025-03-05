package com.example.study.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.study.domain.Item;
import com.example.study.service.ItemService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ItemController {
	private final ItemService itemService;

	@GetMapping("/list")
    public String list(Model model) {
		List<Item> items = itemService.getAllItems();
		
		// (html 파일의 변수명, 전달할 데이터)
        model.addAttribute("items", items);
        
        return "listCategory/list";  // html파일 위치
    }
	
	@GetMapping("/list/create")
    public String create(Model model) {
		
        return "listCategory/create";
    }
	
	@PostMapping("/list/create_process")
    public String createProcess(@RequestParam Map<String, String> formData) {
		itemService.saveItem(formData);

        return "redirect:/list";  // URL로 리다이렉트
    }
	
	@GetMapping("/list/read/{id}")
    public String read(@PathVariable("id") Long id, Model model) {
		Optional<Item> item = itemService.getIdItems(id);
		
		if(item.isPresent()) { // item에 뭔가 들어 있다면
			model.addAttribute("item", item.get());
			return "listCategory/read";
		}
		else {
			return "redirect:/list";
		}
    }
	
	@GetMapping("/list/update/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
		Optional<Item> item = itemService.getIdItems(id);
		
		if(item.isPresent()) { // item에 뭔가 들어 있다면
			model.addAttribute("item", item.get());
			return "listCategory/update";
		}
		else {
			return "redirect:/list";
		}
    }
	
	@PostMapping("/list/update_process")
    public String updateProcess(@RequestParam Map<String, String> formData) {
		Long id = itemService.updateItem(formData);
		
        return "redirect:/list/read/" + id;
    }
	
	@DeleteMapping("/list/delete_process")
    public ResponseEntity<String> deleteProcess(@RequestParam("id") Long id) {
		itemService.deleteItem(id);
		
        return ResponseEntity.status(200).body("삭제완료");
    }
}
