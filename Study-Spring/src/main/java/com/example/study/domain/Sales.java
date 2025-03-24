package com.example.study.domain;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
	
	// ManyToOne : 여러 개의 주문(Sales)이 하나의 Member에 속한다
	// FetchType.LAZY : 필요할 때 가져온다, FetchType.EAGER : 필요없어도 가져온다(default)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEMBER_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT), nullable = false)
	private Member member;
	
	@CreationTimestamp
	@Column(name = "CREATED", nullable = false, updatable = false)
    private Date created;
}
