package com.example.annotation;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.controller.HelloController;

public class ReadMapping {
	
	/**
	 * Method takes the context path and the class object and search for the  @ApiDocs annotation in the method of the class 
	 * and returns a Map containing the context path as key and the description as the value.
	 * 
	 * @param contextPath
	 * @param cls
	 * @return Map<contextpath +requestMapping, Apidocs annotation value>
	 */
	public static void getRestApiInfo(String contextPath,Class<?> cls,Map<String,String> apiInfo) {
		
		try {
			
			//Class<?> cls = HelloController.class;
			
			RequestMapping mappingClass = cls.getAnnotation(RequestMapping.class);
			
			String rootPath = getPath(cls.getName(),mappingClass);
			
			for(Method method : cls.getDeclaredMethods()) {
				
				System.out.println("Class Name>>"+ cls.getName()+"Method Name>>"+method.getName());
				RequestMapping mapping = method.getAnnotation(RequestMapping.class);
				
				if(mapping != null) {
					
					ApiDoc api = method.getAnnotation(ApiDoc.class);
					
					String methodPath = getPath(method.getName(),mapping);
					String methodDocs = getPath(method.getName(),api);
					
					//System.out.println("URL>>"+rootPath+methodPath+"::::Description>>"+methodDocs);
					
					apiInfo.put(contextPath +rootPath+methodPath, methodDocs);
				}
			}
			
		} catch(Exception exception) {
			exception.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		Map<String,String> apiMap = new HashMap<String,String>();
		
		ReadMapping.getRestApiInfo("/services",HelloController.class,apiMap);
		
		apiMap.forEach((k,v) -> System.out.println(k+":"+v));
	}
	
	/**
	 * Method takes the method name and ApiDoc annotation object and returns the annotation value string.
	 * 
	 * @param name	Method name of the method containing the annotation.
	 * @param api	Api documentation object.
	 * @return		ApiDocs annotation objects String value.
	 */
	public static String getPath(String name, ApiDoc api) {
		
		String docs = api.value();
		
		//System.out.println("ApiDoc method>>"+name+":"+docs);
		
		return docs;

	}
	
	/**
	 * Method takes the method name and RequestMapping annotation object and returns the annotation value string.
	 * 
	 * @param name	Method name of the method containing the annotation.
	 * @param api	Api documentation object.
	 * @return		ApiDocs annotation objects String value.
	 */	
	public static String  getPath(String name, RequestMapping mapping) {
		
		String[] pathArray = mapping!=null?mapping.value():null;
		
		String path = (pathArray!=null && pathArray.length>0)?pathArray[0]:null;
		
		//System.out.println("RequestMapping Class/method>>"+name+":"+path);
		
		return path;
	}
	
	public static Map<String,String> parseAnnotation(String contextPath, String filter) throws Exception {
		
		ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        
        provider.addIncludeFilter(new AnnotationTypeFilter(RestController.class));
        
        Set<BeanDefinition> beansList = provider.findCandidateComponents(filter);
        
        Map<String,String> apiMap = new HashMap<String,String>();
        
        for(BeanDefinition bean: beansList) {
        	
        	System.out.println("Name:"+bean.getBeanClassName());
        	System.out.println("================================");
        	
        	//readMapping.getRestApiInfo("/services", bean.getClass());
        			
        	ReadMapping.getRestApiInfo(contextPath ,Class.forName(bean.getBeanClassName()),apiMap);
    		
    		apiMap.forEach((k,v) -> System.out.println(k+":"+v));
        }

        return apiMap;        
	}

}
