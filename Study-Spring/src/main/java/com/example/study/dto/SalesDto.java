package com.example.study.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor  // 모든 필드를 받는 생성자 자동 생성
@NoArgsConstructor   // 기본 생성자 자동 생성
public class SalesDto {
	private String itemName;
	private Integer price;
	private Integer count;
    private String displayName;
    private String username;
    private Date created;
}
