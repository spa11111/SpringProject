package com.virinchi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PagesController {
/*
 * Controller is just like servlet technology. 
 * It handles all the getter and setter functions
 * with the help of HTTP protocol
 * 
 */
	
	//Controller provides: GetMapping and PostMapping
	
	@GetMapping("/normalBook")
	public String normalBook() {
		return "bookDescribe";
	}

	@GetMapping("/premiumBook")
	public String premiumBook() {
		return "bookDescription";
	}
	
	@GetMapping("/chapter")
	public String BookStory() {
		return "readChap";
	}
	
	@GetMapping("/profile")
	public String UserProfile() {
		return "userPage";
	}
	
	@GetMapping("/new")
	public String NewBook() {
		return "addBook";
	}
	
	
}