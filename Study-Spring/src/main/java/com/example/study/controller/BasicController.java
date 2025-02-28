package com.example.study.controller;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.time.ZonedDateTime;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


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
}
