package com.virinchi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController{
	
	@GetMapping("/admin")
	public String Dashboard(HttpSession session) {
		if(session.getAttribute("activeAdmin") != null) {
		return "admin";
		}
		else {
		    return "index";
		}
	}

}
