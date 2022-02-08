package com.edgar.recipeapp.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.edgar.recipeapp.models.Category;

public interface CategoryRep extends CrudRepository<Category, Long> {
	
	Optional<Category> findByDescription(String description);
	
}
