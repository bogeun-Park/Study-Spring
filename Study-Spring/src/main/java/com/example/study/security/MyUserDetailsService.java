package com.example.study.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.study.domain.Member;
import com.example.study.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {  // 로그인시 실행되어 DB에 있는 로그인 정보를 저장
	private final MemberRepository memberRepository;
	
	// DB에서 username을 가진 유저를 찾아 new User(유저아이디, 비번, 권한)을 리턴함
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Member> res = memberRepository.findByUsername(username);
		Member member;
		
		if(res.isPresent()) {
			member = res.get();
		} else {
			throw new UsernameNotFoundException("해당 아이디는 존재하지 않습니다.");
		}
		
		List<GrantedAuthority> authority = new ArrayList<>();
		if(member.getUsername().equals("admin")) { 
			authority.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		} else {
			authority.add(new SimpleGrantedAuthority("ROLE_USER"));
		}
		
		CustomUser user = new CustomUser(member.getUsername(), member.getPassword(), authority); 
		user.setDisplayName(member.getDisplayName());
		user.setId(member.getId());
		
		// Authentication auth 변수 안에 들어감
		// User 또는 User상속한 클래스 타입만 return 가능
		return user;
	}
}
