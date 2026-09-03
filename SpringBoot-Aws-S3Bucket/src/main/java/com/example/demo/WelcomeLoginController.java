package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeLoginController {

	@GetMapping("/sayHi")
	public String sayHi(String str) {
		return "Welcome to Security World";
	}
	
	@GetMapping("/hello")
	public String hello(String str) {
		return "hello Security World";
	}
	

	@GetMapping("/contact")
	public String contact(String str) {
		return "Contact";
	}
	
	@GetMapping("/public/email")
	public String email(String str) {
		return "Contact@gmail.com";
	}
}
