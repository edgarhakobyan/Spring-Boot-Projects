package com.edgar.recipeapp.bootstrap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.edgar.recipeapp.models.Category;
import com.edgar.recipeapp.models.Difficulty;
import com.edgar.recipeapp.models.Ingredient;
import com.edgar.recipeapp.models.Note;
import com.edgar.recipeapp.models.Recipe;
import com.edgar.recipeapp.models.UnitOfMeasure;
import com.edgar.recipeapp.repositories.CategoryRep;
import com.edgar.recipeapp.repositories.RecipeRep;
import com.edgar.recipeapp.repositories.UnitOfMeasureRep;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {
	private final CategoryRep categoryRepository;
    private final RecipeRep recipeRepository;
    private final UnitOfMeasureRep unitOfMeasureRepository;
    
	public RecipeBootstrap(CategoryRep categoryRepository, RecipeRep recipeRepository,
			UnitOfMeasureRep unitOfMeasureRepository) {
		super();
		this.categoryRepository = categoryRepository;
		this.recipeRepository = recipeRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		recipeRepository.saveAll(getRecipes());
	}
	
	private List<Recipe> getRecipes() {
		List<Recipe> recipes = new ArrayList<>();
		
		Optional<UnitOfMeasure> cupsUomOptional = unitOfMeasureRepository.findByDescription("Cup");
        if(!cupsUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }
        Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        if(!teaSpoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }
        UnitOfMeasure teapoonUom = teaSpoonUomOptional.get();
        UnitOfMeasure cupsUom = cupsUomOptional.get();
        
        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
        if(!americanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category Not Found");
        }
        Category americanCategory = americanCategoryOptional.get();
        
        Note guacNotes = new Note();
        guacNotes.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n");
        
        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(0);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed.");
        guacRecipe.setNotes(guacNotes);
        guacRecipe.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), teapoonUom));
        guacRecipe.addIngredient(new Ingredient("Kosher salt", new BigDecimal(".5"), cupsUom));
        guacRecipe.getCategories().add(americanCategory);
        
        recipes.add(guacRecipe);
        
        return recipes;
	}

}
