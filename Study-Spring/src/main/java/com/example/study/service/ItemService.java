package com.example.study.service;

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
    	List<Item> items = itemRepository.findAll(Sort.by(Sort.Order.asc("id"))); 
    	
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

        // price 값을 Integer로 변환
        Integer price = null;
        if (strPrice != null && !strPrice.isEmpty()) {
            price = Integer.parseInt(strPrice);
            
            if (price < 0) {
                throw new IllegalArgumentException("가격은 음수일 수 없습니다.");
            }
        }

        // Item 엔티티 객체 생성
        Item item = new Item();
        item.setTitle(title);
        item.setPrice(price);
        item.setCreated_by(created_by);

        // 데이터베이스에 저장
        itemRepository.save(item);
	}
	
	public Long updateItem(Map<String, String> formData) {
		String strId = formData.get("id");
        String title = formData.get("title");
        String strPrice = formData.get("price");

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

        Item item = new Item();
        item.setId(id);
        item.setTitle(title);
        item.setPrice(price);
        
        itemRepository.save(item);
        
        return id;
	}
	
	public void deleteItem(Long id) {
		itemRepository.deleteById(id);
	}
	
	public Page<Item> getItemPage(int num, int size) {
		Pageable pageable = PageRequest.of(num, size, Sort.by(Sort.Order.asc("id")));
		return itemRepository.findPageBy(pageable);
	}
}
