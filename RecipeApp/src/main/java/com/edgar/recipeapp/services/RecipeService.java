package com.edgar.recipeapp.services;

import java.util.Set;

import com.edgar.recipeapp.models.Recipe;

public interface RecipeService {
	Set<Recipe> getRecipes();
}
