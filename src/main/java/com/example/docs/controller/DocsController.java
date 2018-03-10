package com.example.docs.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.controller.HelloController;
import com.example.utils.AnnotationProcessor;

@RestController
public class DocsController {
	
	@Value("${server.hostname}")
	private String serverName;
	
	@Value("${server.contextPath}")
	private String contextPath;

	@RequestMapping("/docs")
	public Map<String,String> getServiceInfo() throws Exception {
		
		System.out.println("Service Info>>"+contextPath);
		
		//Map<String,String> apiInfo = new HashMap<String,String>();
		
		//ReadMapping.getRestApiInfo(contextPath,HelloController.class,apiInfo);
		
		Map<String,String> apiInfo = AnnotationProcessor.parseAnnotation(serverName+contextPath, "com.example.controller");
		
		return apiInfo;
	}
	
}
