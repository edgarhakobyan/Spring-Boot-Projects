package com.edgar.transaction.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class RequestData {
	@NotNull(message = "App id is required")
	private String appId;
	
	@NotNull(message = "Brand is required")
	private String brand;
	
	@NotNull(message = "Status is required")
	private String status;
	
	@NotNull(message = "Component is required")
	@NotBlank(message = "Component can't be empty")
	private String component;
	
	private String resolution;
	private String code;
	private String processId;
	private String featureId;
	private String userId;
	private String fstid;
	private String uuid;
	private String model;
	private String os;
}
