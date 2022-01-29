package com.edgar.demo.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.edgar.demo.services.ConstructorGreetingService;

class PropertyInjectedControllerTest {
	PropertyInjectedController controller;

	@BeforeEach
	void setUp() throws Exception {
		controller = new PropertyInjectedController();
		controller.greetingService = new ConstructorGreetingService();
	}

	@Test
	void test() {
		System.out.println(controller.getGreeting());
	}

}
