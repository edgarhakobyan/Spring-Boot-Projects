package com.edgar.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.edgar.demo.services.GreetingService;

@Controller
public class PropertyInjectedController {
	@Autowired
	@Qualifier("propertyGreetingService")
	public GreetingService greetingService;
	
	public String getGreeting() {
		return greetingService.sayGreeting();
	}
}
