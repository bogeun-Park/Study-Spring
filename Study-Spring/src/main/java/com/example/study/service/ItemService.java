package com.example.study.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.study.domain.Item;
import com.example.study.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemService {
	private final ItemRepository itemRepository;
	
    public List<Item> getAllItems() {
    	List<Item> items = itemRepository.findAll(Sort.by("id").ascending()); 
    	
        return items;
    }
    
    public Optional<Item> getIdItem(Long id) {
    	// Optional : res가 getIdItem 수도 있고 그렇지 않으면 타입이 Item이다
    	// Optional은 Item 객체를 감싸고 있으므로 get()메서드를 통해서 실제 객체를 꺼낸다
    	Optional<Item> item = itemRepository.findById(id);
    	
        return item;
    }
	
	public void saveItem(Map<String, String> formData) {
		// formData에서 title과 price를 가져오기
        String title = formData.get("title");
        String strPrice = formData.get("price");
        String created_by = formData.get("created_by");
        String strCount = formData.get("count");

        // price 값을 Integer로 변환
        Integer price = null;
        if (strPrice != null && !strPrice.isEmpty()) {
            price = Integer.parseInt(strPrice);
            
            if (price < 0) {
                throw new IllegalArgumentException("가격은 음수일 수 없습니다.");
            }
        }
        
        // count 값을 Integer로 변환
        Integer count = null;
        if (strCount != null && !strCount.isEmpty()) {
        	count = Integer.parseInt(strCount);
            
            if (count < 0) {
                throw new IllegalArgumentException("재고량은 음수일 수 없습니다.");
            }
        }

        // Item 엔티티 객체 생성
        Item item = new Item();
        item.setTitle(title);
        item.setPrice(price);
        item.setCount(count);
        item.setCreated_by(created_by);

        // 데이터베이스에 저장
        itemRepository.save(item);
	}
	
	public Long updateItem(Map<String, String> formData) {
		String strId = formData.get("id");
        String title = formData.get("title");
        String strPrice = formData.get("price");
        String strCount = formData.get("count");

        Long id = null;
        if (strId != null && !strId.isEmpty()) {
        	id = Long.parseLong(strId);
        }
        
        Integer price = null;
        if (strPrice != null && !strPrice.isEmpty()) {
            price = Integer.parseInt(strPrice);
            
            if (price < 0) {
                throw new IllegalArgumentException("가격은 음수일 수 없습니다.");
            }
        }
        
        // count 값을 Integer로 변환
        Integer count = null;
        if (strCount != null && !strCount.isEmpty()) {
        	count = Integer.parseInt(strCount);
            
            if (count < 0) {
                throw new IllegalArgumentException("재고량은 음수일 수 없습니다.");
            }
        }

        Item item = new Item();
        item.setId(id);
        item.setTitle(title);
        item.setPrice(price);
        item.setCount(count);
        
        itemRepository.save(item);
        
        return id;
	}
	
	public void deleteItem(Long id) {
		itemRepository.deleteById(id);
	}
	
	public Page<Item> getItemPage(int num, int size, String searchTxt) {
		Pageable pageable = PageRequest.of(num, size, Sort.by("id").ascending());
		
		if (searchTxt != null && !searchTxt.isBlank()) {
			return itemRepository.findByTitleContaining(pageable, searchTxt);
	    } else {    
	        return itemRepository.findPageBy(pageable);  
	    }
	}
	
	public List<Item> findAllByTitleContains(String searchTxt) {
		if (searchTxt == null || searchTxt.trim().isEmpty()) {
	        return new ArrayList<>(); // 빈 리스트 반환
	    }
		
		List<Item> searchItems = itemRepository.findAllByTitleContains(searchTxt, Sort.by("id").ascending());
	
		return searchItems;
	}
}
