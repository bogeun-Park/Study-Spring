package com.example.study.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = RestController.class)  //@RestController에서 발생한 예외만 처리
public class RestExceptionHandler {
	// RestController의 모든 API에 대해서 에러가 나면 실행됨
	// Exception.class로 모든 API에 대해서 에러가 나지만 여러가지 예외상황에 따라서 처리하도록 함수를 추가해 나간다
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleApiException(Exception e) {
        System.out.println("🚀 REST API 예외 핸들러 실행됨!");
        
        return ResponseEntity.status(400).body("API에서 에러 발생");
    }
}
