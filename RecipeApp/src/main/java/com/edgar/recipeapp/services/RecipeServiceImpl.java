package com.edgar.recipeapp.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.edgar.recipeapp.models.Recipe;
import com.edgar.recipeapp.repositories.RecipeRep;

@Service
public class RecipeServiceImpl implements RecipeService {
	private final RecipeRep recipeRep;
	
	public RecipeServiceImpl(RecipeRep recipeRep) {
		this.recipeRep = recipeRep;
	}

	@Override
	public Set<Recipe> getRecipes() {
		Set<Recipe> recipeSet = new HashSet<>();
		recipeRep.findAll().iterator().forEachRemaining(recipeSet::add);
		
		return recipeSet;
	}

}
