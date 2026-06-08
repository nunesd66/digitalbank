package com.nunesd66.digitalbank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableRetry
public class DigitalbankApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalbankApplication.class, args);
	}

}
