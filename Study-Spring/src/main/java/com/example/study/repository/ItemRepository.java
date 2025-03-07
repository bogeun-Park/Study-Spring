package com.example.study.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.study.domain.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{  // <테이블명, ID컬럼 자료형>
	Page<Item> findPageBy(Pageable page);  // Page<Item> : Item엔티티를 페이지 단위로 가져온다
}
