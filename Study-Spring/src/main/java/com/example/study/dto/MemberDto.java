package com.example.study.dto;

import com.example.study.domain.Member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberDto {
	private String displayName;
	private String username;
	
	public MemberDto(Member member) {
		this.displayName = member.getDisplayName();
		this.username = member.getUsername();
	}
}
