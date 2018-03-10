package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.annotation.ApiDoc;

@RestController
@RequestMapping("/Request")
public class RequestController {
	
	@RequestMapping("/")
	@ApiDoc("This method prints the display name from the request controller")
	public String index() {
		return "Request: Greeting from Spring Boot";
	}
	
	
	@RequestMapping("/request")
	@ApiDoc("This method prints the message 1 from the request controller")
	public String getMessage1() {
		return "Message 1 from Spring Boot";
	}
	
	@RequestMapping("/request2")
	@ApiDoc("This method prints the message 2  from the request controller")
	public String getMessage2() {
		return "Message 2 from Spring Boot";
	}

}
