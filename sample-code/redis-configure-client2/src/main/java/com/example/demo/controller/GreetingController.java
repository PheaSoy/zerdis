package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class GreetingController {

	private final Logger logger = LoggerFactory.getLogger(GreetingController.class);
	
	@Value("${message:default}")
	private String message;

	@GetMapping("/greet")
	String greet() {
		
		logger.info("Greeting call.");
		return  message;
	}
	
}
