package com.example.study.controller;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.study.domain.Sales;
import com.example.study.dto.SalesDto;
import com.example.study.service.SalesService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SalesController {
	private final SalesService salesService;
	
	@PostMapping("/order_process")
	public String postOrder(@RequestParam Map<String, String> formData, Authentication auth) {
		salesService.saveSales(formData, auth);
		
		return "redirect:/list";
	}
	
	@GetMapping("/order")
	public String order(@RequestParam(value = "id", required = false) Long id, Model model) {
		List<Sales> salesList;

	    if (id != null) {
	        salesList = salesService.getSalesById(id);
	    } else {
	        salesList = salesService.getAllSales();
	    }
		
		List<SalesDto> salesDtoList = salesService.getSalesDtoList(salesList);
		
		model.addAttribute("salesDtoList", salesDtoList);
		
		return "orderCategory/read";
	}
}
