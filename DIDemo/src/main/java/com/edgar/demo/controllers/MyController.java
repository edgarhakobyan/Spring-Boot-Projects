package com.edgar.demo.controllers;

import org.springframework.stereotype.Controller;

import com.edgar.demo.services.GreetingService;

@Controller
public class MyController {
	private final GreetingService greetingService;
	
	public MyController(GreetingService greetingService) {
		this.greetingService = greetingService;
	}

	public String sayHallo() {
		return greetingService.sayGreeting();
	}
}
