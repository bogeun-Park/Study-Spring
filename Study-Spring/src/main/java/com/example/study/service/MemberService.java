package com.example.study.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.study.domain.Member;
import com.example.study.dto.MemberDto;
import com.example.study.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	
	public List<Member> getAllMebers() {
    	List<Member> members = memberRepository.findAll(Sort.by(Sort.Order.asc("id"))); 
    	
        return members;
    }
    
    public Optional<Member> getIdItem(Long id) {
    	// Optional : res가 비어있을 수도 있고 그렇지 않으면 타입이 Item이다
    	// Optional은 Item 객체를 감싸고 있으므로 get()메서드를 통해서 실제 객체를 꺼낸다
    	Optional<Member> member = memberRepository.findById(id);
    	
        return member;
    }
	
	public void saveMember(Map<String, String> formData) {
        String displayName = formData.get("displayName");
        String username = formData.get("username");
        String password = formData.get("password");
        
        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(password);

        Member member = new Member();
        member.setDisplayName(displayName);
        member.setUsername(username);
        member.setPassword(encodedPassword);

        memberRepository.save(member);
	}
	
	public MemberDto getMemberDto(Member member) {
		MemberDto memberDto = new MemberDto(member);
		
		return memberDto;
	}
}
