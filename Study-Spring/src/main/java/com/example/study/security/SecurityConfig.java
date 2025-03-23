package com.example.study.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.ExceptionTranslationFilter;

import jakarta.servlet.http.Cookie;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf((csrf) -> csrf.disable());
		http.authorizeHttpRequests((authorize) -> authorize
			.requestMatchers("/**").permitAll()
		);
		
//		http.sessionManagement((session) -> session
//	            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//	    );
		http.addFilterBefore(new JwtFilter(), ExceptionTranslationFilter.class);
		
		http.formLogin((formLogin) -> formLogin
				.loginPage("/login")  // 폼으로 로그인 설정 -> 로그인페이지 URL
				.defaultSuccessUrl("/")
//				.failureUrl("/fail")  이게 없으면 로그인 실패시 /login?error로 이동함
		); 
		
		http.logout(logout -> logout
			.logoutUrl("/logout")
			.logoutSuccessUrl("/login")
			.addLogoutHandler((request, response, authentication) -> {
			// jwt 로그인시 로그아웃하면 jwt 쿠키 삭제
			Cookie cookie = new Cookie("jwt", "");
			cookie.setMaxAge(0);
			cookie.setPath("/");
			response.addCookie(cookie);
			})
		);
		
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
