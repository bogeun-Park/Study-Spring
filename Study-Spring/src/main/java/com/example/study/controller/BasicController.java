package com.example.study.controller;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.time.ZonedDateTime;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class BasicController {	
	@GetMapping("/")
    public String index(Model model) {
        model.addAttribute("name", "나야나");  // 전달할 변수명, 전달할 데이터
        return "index";
    }
    
    @GetMapping("/time")
    @ResponseBody
    public String time() {
    	return ZonedDateTime.now().toString();
    }
    
    @Autowired
    private DataSource dataSource;
    
    @GetMapping("/db")
    @ResponseBody
    public String testDBConnection() {
        try (Connection connection = dataSource.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            return "DB 연결 성공! URL: " + metaData.getURL();
        } catch (Exception e) {
            return "DB 연결 실패: " + e.getMessage();
        }
    }
    
    @GetMapping("/err")
    public void err() throws Exception {
        throw new Exception();
    }
    
    @GetMapping("/hash")
    public String hash() {
    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        // 비밀번호 암호화
        String rawPassword = "aa";
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println("Encoded Password: " + encodedPassword);
        
        // 비밀번호 검증
        boolean matches = encoder.matches("aa", encodedPassword); // "aa"가 암호화된 비밀번호와 일치하는지 확인
        System.out.println("Password matches: " + matches);
        
		return "redirect:/list";
    }
}

@RestController
class RestComtroller {
	@GetMapping("/rest")
	public String rest() {
		return "restAPI 호출쓰";
	}
	
	@GetMapping("/resterr")
	public String resterr() throws Exception {
		throw new Exception();
	}
}