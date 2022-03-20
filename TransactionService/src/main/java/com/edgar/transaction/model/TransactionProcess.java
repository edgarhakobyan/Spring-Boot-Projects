package com.edgar.transaction.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import lombok.Data;

@Data
@Entity
public class TransactionProcess {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "fk_component_id", nullable = false)
	private Component component;
	
	@ManyToOne
	@JoinColumn(name = "fk_status_id", nullable = false)
	private Status status;
	
	@ManyToOne
	@JoinColumn(name = "fk_app_brand_config_id")
	private AppBrandConfig appBrandConfig;
	
	@ManyToOne
	@JoinColumn(name = "fk_transaction_id")
	private Transaction transaction;
	
	@ManyToOne
	@JoinColumn(name = "fk_resolution_id")
	private Resolution resolution;
	
	private String processId;
	private String userEmail;
	private String fstid;
	private String uuid;
	private String model;
	private String os;
	private Date createdAt;
	
	@PrePersist
	void createdAt() {
		this.createdAt = new Date();
	}
}
