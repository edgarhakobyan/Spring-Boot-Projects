package com.edgar.taco.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.edgar.taco.models.Ingredient;
import com.edgar.taco.models.Order;
import com.edgar.taco.models.Taco;
import com.edgar.taco.repositories.IngredientRepository;
import com.edgar.taco.repositories.TacoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class TacoController {
	private IngredientRepository ingredientRepository;
	private TacoRepository tacoRepository;
	
	public TacoController(IngredientRepository ingredientRepository, TacoRepository tacoRepository) {
		this.ingredientRepository = ingredientRepository;
		this.tacoRepository = tacoRepository;
	}
	
	@ModelAttribute(name = "order")
	public Order order() {
		return new Order();
	}
	
	@ModelAttribute(name = "taco")
	public Taco taco() {
		return new Taco();
	}

	@GetMapping
	public String getDesign(Model model) {
		List<Ingredient> ingredients = new ArrayList<>();
		ingredientRepository.findAll().forEach((obj) -> ingredients.add(obj));
		
		System.out.println(ingredients);
		
		for (String type : Ingredient.TYPES) {
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
		}
		model.addAttribute("design", new Taco());
	    return "design";
	}
	
	@PostMapping
	public String processDesign(@Valid Taco design, Errors errors, @ModelAttribute Order order) {
		if (errors.hasErrors()) {
			log.error("TacoController error - " + errors.toString());
			return "redirect:/design";
		}
		log.info("Processing design: " + design);
		Taco savedTaco = tacoRepository.save(design);
//		order.addDe
		
		return "redirect:/orders/current";
	}
	
	private List<Ingredient> filterByType(List<Ingredient> ingredients, String type) {
		return ingredients
				.stream().
				filter((t) -> t.getType().equals(type))
				.collect(Collectors.toList());
	}
}
