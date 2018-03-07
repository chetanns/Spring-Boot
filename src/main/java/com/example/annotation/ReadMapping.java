package com.example.annotation;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.controller.HelloController;

public class ReadMapping {
	
	public static Map<String,String> getRestApiInfo(String contextPath) {
		
		Map<String,String> apiInfo = new HashMap<String,String>();
		
		try {
			
			Class<?> cls = HelloController.class;
			
			RequestMapping mappingClass = cls.getAnnotation(RequestMapping.class);
			
			String rootPath = getPath(cls.getName(),mappingClass);
			
			for(Method method : cls.getDeclaredMethods()) {
				
				RequestMapping mapping = method.getAnnotation(RequestMapping.class);
				
				ApiDoc api = method.getAnnotation(ApiDoc.class);
				
				String methodPath = getPath(method.getName(),mapping);
				String methodDocs = getPath(method.getName(),api);
				
				//System.out.println("URL>>"+rootPath+methodPath+"::::Description>>"+methodDocs);
				
				apiInfo.put(contextPath +rootPath+methodPath, methodDocs);
			}
			
		} catch(Exception exception) {
			exception.printStackTrace();
		}

		return apiInfo;
	}
	

	public static void main(String[] args) {
		
		Map<String,String> apiMap = ReadMapping.getRestApiInfo("/services");
		
		apiMap.forEach((k,v) -> System.out.println(k+":"+v));
	}
	
	public static String getPath(String name, ApiDoc api) {
		
		String docs = api.value();
		
		//System.out.println("ApiDoc method>>"+name+":"+docs);
		
		return docs;

	}
	
	public static String  getPath(String name, RequestMapping mapping) {
		
		String[] pathArray = mapping!=null?mapping.value():null;
		
		String path = (pathArray!=null && pathArray.length>0)?pathArray[0]:null;
		
		//System.out.println("RequestMapping Class/method>>"+name+":"+path);
		
		return path;
	}

}
