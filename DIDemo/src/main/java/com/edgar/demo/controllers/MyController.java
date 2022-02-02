package com.edgar.demo.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import com.edgar.demo.services.GreetingService;

@Controller
public class MyController {
	private final GreetingService greetingService;
	
	@Value("${demo.username}")
	private String testProperty;
	
	public MyController(GreetingService greetingService) {
		this.greetingService = greetingService;
	}

	public String sayHallo() {
		System.out.println("username " + testProperty);
		return greetingService.sayGreeting();
	}
}
