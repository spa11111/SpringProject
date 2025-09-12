package com.virinchi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class PagesController {
/*
 * Controller is just like servlet technology. 
 * It handles all the getter and setter functions
 * with the help of HTTP protocol
 * 
 */
	
	//Controller provides: GetMapping and PostMapping

	@GetMapping("/premiumBook")
	public String PremiumStory() {
		return "trypremium";
	}
	
	@GetMapping("/chapter")
	public String BookStory(HttpSession session) {
		if(session.getAttribute("activeUser") != null) {
		return "readChap";
		}
		else
        {
            return "index";
        }
	}

	
	
	
}