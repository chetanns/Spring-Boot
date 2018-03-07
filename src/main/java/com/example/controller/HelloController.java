package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.annotation.ApiDoc;

@RestController
@RequestMapping("/Test")
public class HelloController {
	
	@RequestMapping("/")
	@ApiDoc("This method prints the display name")
	public String index() {
		return "Greeting from Spring Boot";
	}
	
	
	@RequestMapping("/Test1")
	@ApiDoc("This method prints the message 1")
	public String getMessage1() {
		return "Message 1 from Spring Boot";
	}
	
	@RequestMapping("/Test2")
	@ApiDoc("This method prints the message 2")
	public String getMessage2() {
		return "Message 2 from Spring Boot";
	}

}
