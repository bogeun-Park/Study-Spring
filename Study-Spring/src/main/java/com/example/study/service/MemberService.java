package com.example.study.service;

import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.study.domain.Member;
import com.example.study.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	
	public void saveMember(Map<String, String> formData) {
        String displayName = formData.get("displayName");
        String userName = formData.get("userName");
        String password = formData.get("password");
        
        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(password);

        Member member = new Member();
        member.setDisplayName(displayName);
        member.setUserName(userName);
        member.setPassword(encodedPassword);

        memberRepository.save(member);
	}
}
