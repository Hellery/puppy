package com.puppy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class PuppyApplication {
	public static void main(String[] args) {
		SpringApplication.run(PuppyApplication.class, args);
	}
}
