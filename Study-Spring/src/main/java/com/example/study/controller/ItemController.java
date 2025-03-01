package com.example.study.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.study.domain.Item;
import com.example.study.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ItemController {
	private final ItemRepository itemRepository;

	@GetMapping("/list")
    public String list(Model model) {
		// itemRepository테이블의 모든 행을 리스트형태로 반환
		List<Item> items = itemRepository.findAll(Sort.by(Sort.Order.asc("id")));
		
		// (html 파일의 변수명, 전달할 데이터)
        model.addAttribute("items", items);
        return "list";
    }
	
	@GetMapping("/write")
    public String write() {
        
        return "write";
    }
	
	@PostMapping("/write_process")
    public String addPost1(@RequestParam Map<String, String> formData) {		
		// formData에서 title과 price를 가져오기
        String title = formData.get("title");
        String priceStr = formData.get("price");

        // price 값을 Integer로 변환
        Integer price = null;
        try {
            if (priceStr != null && !priceStr.isEmpty()) {
                price = Integer.parseInt(priceStr);
            }
        } catch (NumberFormatException e) {
            // 가격이 숫자가 아니면 예외 처리
            e.printStackTrace();
        }

        // Item 엔티티 객체 생성
        Item item = new Item();
        item.setTitle(title);
        item.setPrice(price);

        // 데이터베이스에 저장
        itemRepository.save(item);

        // 저장 후 리다이렉트
        return "redirect:/list";
    }
	
	@PostMapping("/add2")
    public String addPost2(@ModelAttribute Item item) {		
        itemRepository.save(item);

        return "redirect:/list";
    }
	
	@GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
		// Optional : res가 비어있을 수도 있고 그렇지 않으면 타입이 Item이다
		// Optional은 Item 객체를 감싸고 있으므로 get()메서드를 통해서 실제 객체를 꺼낸다
		Optional<Item> item = itemRepository.findById(id);
		
		if(item.isPresent()) { // item에 뭔가 들어 있다면
			model.addAttribute("item", item.get());
			return "detail";
		}
		else {
			return "redirect:/list";
		}
    }
}
