package com.edgar.jwtdemo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class User {
	@Id
	private Long id;
	private String username;
	private String password;
}
