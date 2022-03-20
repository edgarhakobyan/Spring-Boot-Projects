package com.edgar.transaction.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Status {
	@Id
	private Long id;
	private String name;
}
