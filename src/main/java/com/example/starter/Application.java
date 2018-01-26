package com.example.starter;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example")
public class Application {

	public static void main(String[] args) {
		
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		
		return args -> {
			
			System.out.println("Lets inspect the bean provided by the Spring Bean");
			
			String[]beanNames = ctx.getBeanDefinitionNames();
			
			Arrays.sort(beanNames);
			
			for(String beanName:beanNames) {
				
				System.out.println(beanName);
			}
			
		};
	}
}
