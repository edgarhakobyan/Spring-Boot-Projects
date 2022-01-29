package com.edgar.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edgar.demo.services.JokeService;

@Controller
public class JokeController {
	private final JokeService jokeService;
	
	public JokeController(JokeService jokeService) {
		this.jokeService = jokeService;
	}
	
	@RequestMapping({"/", "/joke"})
	public String showJoke(Model model) {
		model.addAttribute("joke", jokeService.getJoke());
		return "temp";
	}

}
