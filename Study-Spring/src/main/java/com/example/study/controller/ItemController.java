package com.example.study.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.study.domain.Comments;
import com.example.study.domain.Item;
import com.example.study.service.CommentService;
import com.example.study.service.ItemService;
import com.example.study.service.OracleStorageService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ItemController {
	private final ItemService itemService;
	private final CommentService commentService;
	private final OracleStorageService oracleStorageService;

	@GetMapping("/list")
    public String listPage(@RequestParam(value = "page", defaultValue = "1") int num, 
    					   @RequestParam(value = "search", required = false) String searchTxt,
    					   Model model) {		
		// 페이지 번호는 1부터 시작하므로, (num - 1)로 설정
		// (몇번째 페이지, 페이지당 몇개)
	    Page<Item> items = itemService.getItemPage((num - 1), 3, searchTxt);
	    
	    // 총 페이지 수와 현재 페이지
	    int totalPages = items.getTotalPages();  // 전체 페이지 수
	    int currentPage = items.getNumber() + 1;  // 현재 페이지 번호 (0부터 시작하므로 1을 더해줍니다)
	    
	    if(totalPages == 0) totalPages = 1;
		
        model.addAttribute("items", items);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("searchTxt", searchTxt);
        
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
		Optional<Item> item = itemService.getIdItem(id);
		List<Comments> comment = commentService.findAllByParentId(id);
		
		if(item.isPresent()) { // item에 뭔가 들어 있다면
			model.addAttribute("item", item.get());
			model.addAttribute("comment", comment);
			return "listCategory/read";
		}
		else {
			return "redirect:/list";
		}
    }
	
	@GetMapping("/list/update/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
		Optional<Item> item = itemService.getIdItem(id);
		
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
	
	@GetMapping("/presigned-url")
	@ResponseBody
	public String getUrl(@RequestParam("filename") String filename) {        
        String presignedUrl = oracleStorageService.createPresignedUrl(filename);
        
        return presignedUrl;
    }
}
