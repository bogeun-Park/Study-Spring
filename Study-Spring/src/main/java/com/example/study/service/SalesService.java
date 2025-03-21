package com.example.study.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.study.domain.Member;
import com.example.study.domain.Sales;
import com.example.study.dto.SalesDto;
import com.example.study.repository.SalesRepository;
import com.example.study.security.CustomUser;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SalesService {
	private final SalesRepository salesRepository;
	
	public List<Sales> getAllSales() {
		List<Sales> sales = salesRepository.customFindAll();
		
		return sales;
	}
	
	public List<Sales> getSalesById(Long id) {
		List<Sales> sales = salesRepository.customFindById(id);
		
		return sales;
	}
	
	public void saveSales(Map<String, String> formData, Authentication auth) {
        String itemName = formData.get("itemName");
        String strPrice = formData.get("price");
        String strCount = formData.get("count");
        
        Integer price = null;
        if (strPrice != null && !strPrice.isEmpty()) {
            price = Integer.parseInt(strPrice);
            
            if (price < 0) {
                throw new IllegalArgumentException("가격은 음수일 수 없습니다.");
            }
        }
        
        Integer count = null;
        if (strCount != null && !strCount.isEmpty()) {
        	count = Integer.parseInt(strCount);
            
            if (count < 0) {
                throw new IllegalArgumentException("수량은 음수일 수 없습니다.");
            }
        }
        
        // @ManyToOne으로 되어 있으므로 member의 id만 세팅하면 됨
        CustomUser user = (CustomUser) auth.getPrincipal();
        Member member = new Member();
        member.setId(user.getId());

        Sales sales = new Sales();
        sales.setItemName(itemName);
        sales.setPrice(price);
        sales.setCount(count);
        sales.setMember(member);

        salesRepository.save(sales);
	}
	
	public List<SalesDto> getSalesDtoList(List<Sales> salesList) {
		List<SalesDto> salesDtoList = new ArrayList<>();
	    
	    for (Sales sales : salesList) {
	        SalesDto salesDto = new SalesDto(
	                sales.getItemName(),
	                sales.getPrice(),
	                sales.getCount(),
	                sales.getMember().getDisplayName(),
	                sales.getMember().getUsername(),
	                sales.getCreated());
	        salesDtoList.add(salesDto);
	    }
	    
	    return salesDtoList;
	}
}
