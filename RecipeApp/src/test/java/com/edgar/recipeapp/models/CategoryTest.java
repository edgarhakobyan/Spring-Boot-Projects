package com.edgar.recipeapp.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CategoryTest {
	Category category;

	@BeforeEach
	void setUp() throws Exception {
		category = new Category();
	}

	@Test
	void getId() throws Exception {
		category.setId(5L);
		assertEquals(5l, category.getId());
	}

}
