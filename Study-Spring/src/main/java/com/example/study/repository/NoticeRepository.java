package com.example.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.study.domain.Notice;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {

}
