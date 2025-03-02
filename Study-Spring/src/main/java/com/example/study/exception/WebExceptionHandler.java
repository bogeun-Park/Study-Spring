package com.example.study.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletResponse;

@ControllerAdvice  //@Controller에서 발생한 예외만 처리
public class WebExceptionHandler {	
	// Controller의 모든 API에 대해서 에러가 나면 실행됨
//	@ExceptionHandler(Exception.class)
//	public String handleWebException(Exception e, Model model, HttpServletResponse response) {
//		System.out.println("🌍 웹 예외 핸들러 실행됨!");
//        
//		int statusCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR; // 기본값 500
//	    String message = e.getMessage();
//		response.setStatus(statusCode);
//		
//        model.addAttribute("status", statusCode);
//        model.addAttribute("message", message);
//        
//        return "error";  // error.html 뷰 반환
//	}
}
