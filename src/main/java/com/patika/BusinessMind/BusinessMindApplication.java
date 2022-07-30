package com.patika.BusinessMind;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.patika.BusinessMind.ConsoleResponseHandler.Start;

@SpringBootApplication
public class BusinessMindApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusinessMindApplication.class, args);
		Start.firstScreen();
	}

}
