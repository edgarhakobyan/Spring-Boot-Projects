package com.edgar.recipeapp.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.edgar.recipeapp.models.Recipe;
import com.edgar.recipeapp.repositories.RecipeRep;

class RecipeServiceImplTest {
	RecipeService recipeService;
	
	@Mock
	RecipeRep recipeRep;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		
		recipeService = new RecipeServiceImpl(recipeRep);
	}

	@Test
	void getRecipes() throws Exception {
		Recipe recipe = new Recipe();
		HashSet<Recipe> recipeData = new HashSet<>();
		recipeData.add(recipe);
		
		when(recipeRep.findAll()).thenReturn(recipeData);
		
		Set<Recipe> recipes = recipeService.getRecipes();
		assertEquals(recipes.size(), 1);
		
		verify(recipeRep, times(1)).findAll();
	}

}
