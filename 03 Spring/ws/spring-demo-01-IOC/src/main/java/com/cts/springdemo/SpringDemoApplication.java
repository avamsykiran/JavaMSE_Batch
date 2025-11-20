package com.cts.springdemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.cts.springdemo.ui.SimpleUI;

public class SpringDemoApplication {
	
	public static void main(String[] args) {		
		ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
				
		SimpleUI ui = (SimpleUI) context.getBean("simpleUI");
		ui.run();
	}

}
