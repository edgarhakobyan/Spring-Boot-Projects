package com.edgar.taco.models;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
public class Ingredient {
	@Id
	private final String id;
	private final String name;
	private final String type;
	
	public static String[] TYPES = {"WRAP", "PROTEIN", "VEGGIES", "CHEESE", "SAUCE"};
//	public static enum Type {
//		WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
//    }
}
