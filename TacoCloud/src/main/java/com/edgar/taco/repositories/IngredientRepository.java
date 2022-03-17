package com.edgar.taco.repositories;

import org.springframework.data.repository.CrudRepository;

import com.edgar.taco.models.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
//	Iterable<Ingredient> findAll();
//	Ingredient findOne(String id);
//	Ingredient save(Ingredient ingredient);
}
