package com.example.study.service;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.study.domain.Comments;
import com.example.study.repository.CommentRepository;
import com.example.study.security.CustomUser;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
	private final CommentRepository commentRepository;
	
	public Long saveComment(Map<String, String> formData, Authentication auth) {
		CustomUser user = (CustomUser) auth.getPrincipal();
        String content = formData.get("content");
        String strParentId = formData.get("parent_id");
        
        Long parent_id = null;
        if (strParentId != null && !strParentId.isEmpty()) {
        	parent_id = Long.parseLong(strParentId);
        }
        
        // Item 엔티티 객체 생성
        Comments comment = new Comments();
        comment.setUsername(user.getUsername());
        comment.setContent(content);
        comment.setParentId(parent_id);

        // 데이터베이스에 저장
        commentRepository.save(comment);
        
        return parent_id;
	}
	
	public List<Comments> findAllByParentId(Long id) {
    	List<Comments> comment = commentRepository.findAllByParentId(id);
    	
        return comment;
    }
}
