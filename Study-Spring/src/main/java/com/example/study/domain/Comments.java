package com.example.study.domain;

import groovy.transform.ToString;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@ToString
public class Comments {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_comment")
    @SequenceGenerator(name = "seq_comment", sequenceName = "SEQ_COMMNET", allocationSize = 1)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "USERNAME", nullable = false)
	private String username;
	
	@Column(name = "CONTENT")
	private String content;
	
	@Column(name = "PARENT_ID", nullable = false) 
	private Long parentId;
}
