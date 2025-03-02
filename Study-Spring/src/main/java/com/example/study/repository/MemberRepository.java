package com.example.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.study.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{

}
