package com.example.study.domain;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
	
	@Column(name = "TITLE", nullable = false)
	private String title;

    @Column(name = "CONTENT", nullable = false)
    private String content;
    
    @Column(name = "CREATED", nullable = false)
    private Date created;

    @PrePersist
    public void onPrePersist() {
        if (this.created == null) {
            this.created = new java.sql.Date(System.currentTimeMillis()); // 현재 날짜로 설정
        }
    }
}
