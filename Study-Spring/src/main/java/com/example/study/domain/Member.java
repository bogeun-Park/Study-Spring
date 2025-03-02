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
public class Member {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_member")
    @SequenceGenerator(name = "seq_member", sequenceName = "SEQ_MEMBER", allocationSize = 1)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "DISPLAYNAME", nullable = false)
	private String displayName;
	
	@Column(name = "USERNAME", nullable = false, unique = true)
	private String userName;
	
	@Column(name = "PASSWORD", nullable = false)
	private String password;
}
