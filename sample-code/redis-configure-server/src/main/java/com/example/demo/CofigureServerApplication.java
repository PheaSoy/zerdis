package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import zerdis.annotation.EnableRedisConfigureServer;

@SpringBootApplication
@EnableRedisConfigureServer
public class CofigureServerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CofigureServerApplication.class, args);
	}
	
	@Value("${message:default}")
	String message;

	@Override
	public void run(String... args) throws Exception {

		System.out.println("message:" + message);
	}
}
