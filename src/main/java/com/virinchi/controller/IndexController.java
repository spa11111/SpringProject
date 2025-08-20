package com.virinchi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
/*
 * Controller is just like servlet technology. 
 * It handles all the getter and setter functions
 * with the help of HTTP protocol
 * 
 */
	
	//Controller provides: GetMapping and PostMapping
	
	@GetMapping("/")
	public String myFirstPage() {
		return "index";
	}

	@GetMapping("/nextpage")
	public String myNextPage() {
		return "nextPage";
	}
	
	
}