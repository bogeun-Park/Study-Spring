package com.example.study.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
	private String username;
	
	@Column(name = "PASSWORD", nullable = false)
	private String password;
	
	// OneToMany : member 하나는 여러 개의 Sales를 가진다 (@현재테이블To상대테이블)
	// mappedBy = "member" : Sales엔티티의 member컬럼에 속한다
	@ToString.Exclude
	@OneToMany(mappedBy = "member")
	private List<Sales> sales = new ArrayList<>();
}
