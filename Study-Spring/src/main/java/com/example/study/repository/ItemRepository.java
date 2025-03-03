package com.example.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.study.domain.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{  // <테이블명, ID컬럼 자료형>
	
}
