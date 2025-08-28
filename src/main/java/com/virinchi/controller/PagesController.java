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
	
	@GetMapping("/profile")
	public String UserProfile(HttpSession session) {
		if(session.getAttribute("activeUser") != null) {
		return "userPage";
		}
		else
        {
            return "index";
        }
	}
	
	@GetMapping("/new")
	public String NewBook(HttpSession session) {
		if(session.getAttribute("activeUser") != null) {
		return "addBook";
		}
		else
        {
            return "index";
        }
	}
	
	
}