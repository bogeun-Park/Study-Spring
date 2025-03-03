package com.example.study.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf((csrf) -> csrf.disable());
		http.authorizeHttpRequests((authorize) ->
			authorize.requestMatchers("/**").permitAll()
		);
		http.formLogin((formLogin) -> formLogin.loginPage("/login")
			.defaultSuccessUrl("/")
//			.failureUrl("/fail")  이게 없으면 로그인 실패시 /login?error로 이동함
		); 
		http.logout(logout -> logout
			.logoutUrl("/logout")
			.logoutSuccessUrl("/login")   
		);
		
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
