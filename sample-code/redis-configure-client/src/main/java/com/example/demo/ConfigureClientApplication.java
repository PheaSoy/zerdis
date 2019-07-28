package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import zerdis.annotation.EnableRedisConfigureClient;

@SpringBootApplication
@EnableRedisConfigureClient
public class ConfigureClientApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ConfigureClientApplication.class, args);
	}
	
	@Value("${message:default}")
	String message;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("message:" + message);
	}
	
	
}
