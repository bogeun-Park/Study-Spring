package com.example.study.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Item {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_item")
    @SequenceGenerator(name = "seq_item", sequenceName = "SEQ_ITEM", allocationSize = 1)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "TITLE", nullable = false)
	private String title;
	
	@Column(name = "PRICE", nullable = false)
	private Integer price;
	
	@Column(name = "CREATED_BY")
	private String created_by;
}
