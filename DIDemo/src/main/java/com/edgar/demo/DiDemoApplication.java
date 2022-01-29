package com.edgar.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.edgar.demo.controllers.ConstructorInjectedController;
import com.edgar.demo.controllers.MyController;
import com.edgar.demo.controllers.MyGreetingServiceController;
import com.edgar.demo.controllers.PropertyInjectedController;
import com.edgar.demo.controllers.SetterInjectedController;

@SpringBootApplication
public class DiDemoApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DiDemoApplication.class, args);

		MyController myController = (MyController) context.getBean("myController");
		System.out.println(myController.sayHallo());

		System.out.println("=========== Property");
		PropertyInjectedController propertyInjectedController = (PropertyInjectedController) context.getBean("propertyInjectedController");
		System.out.println(propertyInjectedController.getGreeting());

		System.out.println("=========== Setter");
		SetterInjectedController setterInjectedController = (SetterInjectedController) context.getBean("setterInjectedController");
		System.out.println(setterInjectedController.getGreeting());

		System.out.println("=========== Constructor");
		ConstructorInjectedController constructorInjectedController = (ConstructorInjectedController) context.getBean("constructorInjectedController");
		System.out.println(constructorInjectedController.getGreeting());

		System.out.println("=========== Profile");
		MyGreetingServiceController myGreetingServiceController = (MyGreetingServiceController) context.getBean("myGreetingServiceController");
		System.out.println(myGreetingServiceController.sayHello());
	}

}
