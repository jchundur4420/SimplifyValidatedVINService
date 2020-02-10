package com.playground;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.playground.controller.VINRequestController;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan
public class VinSimplifiedPlaygroundAppApplication implements CommandLineRunner {
	
	/*
	 * @Autowired VINRequestController controller;
	 */

	public static void main(String[] args) {
		SpringApplication.run(VinSimplifiedPlaygroundAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		//controller.isValid("1FAHP3K27CL106954");
	}

}
