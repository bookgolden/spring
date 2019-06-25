package com.java.test;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.java.tx.TxConfig;
import com.java.tx.UserService;

public class IOCTest_Tx {
	
	@Test
	public void test01(){
		AnnotationConfigApplicationContext applicationContext = 
				new AnnotationConfigApplicationContext(TxConfig.class);
	
		UserService userService = applicationContext.getBean(UserService.class);
		
		userService.insertUser();
		applicationContext.close();
	}

}
