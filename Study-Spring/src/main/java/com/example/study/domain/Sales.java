package com.example.study.domain;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Sales {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_sales")
    @SequenceGenerator(name = "seq_sales", sequenceName = "SEQ_SALES", allocationSize = 1)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "ITEMNAME", nullable = false)
	private String itemName;
	
	@Column(name = "PRICE", nullable = false)
	private Integer price;
	
	@Column(name = "COUNT", nullable = false)
	private Integer count;
	
	@ManyToOne  // 여러 개의 주문(Sales)을 하나의 Member가 가질 수 있음(다대일)
	@JoinColumn(name = "MEMBER_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT), nullable = false)
	private Member member;
	
	@CreationTimestamp
	@Column(name = "CREATED", nullable = false)
    private Date created;
}
