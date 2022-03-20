package com.edgar.transaction.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class AppBrandConfig {
	@Id
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "fk_app_id")
	private AppId appId;
	
	@ManyToOne
	@JoinColumn(name = "fk_brand_id")
	private Brand brand;
	
	private String functionality;
	
	private boolean isEnabled;
}
