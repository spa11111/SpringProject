package com.virinchi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddController {

	@GetMapping("/new")
	public String AddBook() {
		return "addBook";
	}
}
