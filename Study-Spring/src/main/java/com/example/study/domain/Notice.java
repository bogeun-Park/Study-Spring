package com.example.study.domain;

import java.sql.Date;

import groovy.transform.ToString;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@ToString
public class Notice {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_notice")
    @SequenceGenerator(name = "seq_notice", sequenceName = "SEQ_NOTICE", allocationSize = 1)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "title", nullable = false)
	private String title;

    @Column(name = "content", nullable = false)
    private String content;
    
    @Column(name = "created", nullable = false)
    private Date created;

    @PrePersist
    public void onPrePersist() {
        if (this.created == null) {
            this.created = new java.sql.Date(System.currentTimeMillis()); // 현재 날짜로 설정
        }
    }
}
