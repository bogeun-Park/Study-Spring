package com.example.study.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.study.domain.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{  // <테이블명, ID컬럼 자료형>
	Page<Item> findPageBy(Pageable pageable);  // Page<Item> : Item엔티티를 페이지 단위로 가져온다
	
	// jpa의 기본 문법 but 데이터가 많을 경우 속도가 느림(나중에 index 적용해보기)
	List<Item> findAllByTitleContains(String searchTxt, Sort sort);  // search이 포함된 데이터를 가져온다
	
	@Query(value = "select * from item where id = ?1", nativeQuery = true)
	Item rawQuery(Long num);
}
