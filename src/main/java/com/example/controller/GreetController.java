package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.annotation.ApiDoc;

@RestController
@RequestMapping("/Greet")
public class GreetController {
	
	@RequestMapping("/")
	@ApiDoc("This method prints the display name")
	public String index() {
		return "Greet: Greeting from Spring Boot";
	}
	
	
	@RequestMapping("/greet1")
	@ApiDoc("This method prints the message 1 from greetController")
	public String getMessage1() {
		return "Message 1 from Spring Boot";
	}
	
	@RequestMapping("/greet2")
	@ApiDoc("This method prints the message 2 from greetController")
	public String getMessage2() {
		return "Message 2 from Spring Boot";
	}

}
