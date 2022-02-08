package com.edgar.recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.edgar.recipeapp.models.Recipe;

public interface RecipeRep extends CrudRepository<Recipe, Long> {

}
