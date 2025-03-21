package com.example.study.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.study.domain.Sales;

public interface SalesRepository extends JpaRepository<Sales, Long>{
	// ManyToOne은 성능문제가 생겨서 join문을 이용해 해결함(JPQL문법)
	// SELECT * FROM sales s JOIN member m ON s.member_id = m.id
	@Query(value = "SELECT s FROM Sales s JOIN FETCH s.member ORDER BY s.id ASC")
	List<Sales> customFindAll();
	
	@Query("SELECT s FROM Sales s JOIN FETCH s.member m WHERE m.id = :id ORDER BY s.id ASC")
    List<Sales> customFindById(@Param("id") Long id);
}
