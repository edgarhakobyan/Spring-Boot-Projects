package com.edgar.demo.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service("myGreetingService")
@Profile("ES")
public class MySpanishGreetingService implements GreetingService {
	@Override
	public String sayGreeting() {
		return "Hello World - ES";
	}
}
