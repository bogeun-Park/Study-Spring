package com.example.study.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.study.domain.Comments;

public interface CommentRepository extends JpaRepository<Comments, Long>{
	List<Comments> findAllByParentId(Long parentId);
}
