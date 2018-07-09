package com.example.tdddemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={
"com.example.tdddemo"})
public class TdddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TdddemoApplication.class, args);
	}
}
