package com.virinchi.controller;

import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

public class DescribeController {
	
	@GetMapping("/normalBook")
	  
	public String normalBook(HttpSession session) {
		if(session.getAttribute("activeUser") != null) {
		return "bookDescribe";
        }
        else
        {
            return "index";
        }
	}

	@GetMapping("/premiumBook")
	public String premiumBook(HttpSession session) {
		if(session.getAttribute("activeUser") != null) {
		return "bookDescription";
		}
		else
        {
            return "index";
        }
		
	}

}
