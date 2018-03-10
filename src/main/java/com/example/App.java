package com.example;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.web.bind.annotation.RestController;

import com.example.annotation.ApiDoc;
import com.example.controller.HelloController;
import com.example.utils.AnnotationProcessor;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        System.out.println( "Hello World!" );
        
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        
        provider.addIncludeFilter(new AnnotationTypeFilter(RestController.class));
        
        Set<BeanDefinition> beansList = provider.findCandidateComponents("com.example.controller");
        
        System.out.println("Beans loop>>"+beansList.size());
        
        for(BeanDefinition bean: beansList) {
        	
        	System.out.println("Name:"+bean.getBeanClassName());
        	System.out.println("================================");
        	
        	//readMapping.getRestApiInfo("/services", bean.getClass());
        	
        	Map<String,String> apiMap = new HashMap<String,String>();
        	
        	AnnotationProcessor.getRestApiInfo("/services",Class.forName(bean.getBeanClassName()),apiMap);
    		
    		apiMap.forEach((k,v) -> System.out.println(k+":"+v));
        }
    }
}
