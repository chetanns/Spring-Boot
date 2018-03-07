package com.example.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.annotation.ReadMapping;

@RestController
public class DocsController {
	
	@Value("${server.contextPath}")
	private String contextPath;

	@RequestMapping("/docs")
	public Map<String,String> getServiceInfo() {
		
		System.out.println("Service Info>>"+contextPath);
		
		return ReadMapping.getRestApiInfo(contextPath);
	}
	
}
