package com.edgar.demo.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.edgar.demo.services.GreetingService;

@Controller
public class MyGreetingServiceController {
	private final GreetingService greetingService;

	public MyGreetingServiceController(@Qualifier("myGreetingService") GreetingService greetingService) {
		this.greetingService = greetingService;
	}
	
	public String sayHello() {
		return greetingService.sayGreeting();
	}
}
