package com.virinchi.controller;

import jakarta.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.virinchi.model.Admin;
import com.virinchi.repository.AdminRepository;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class AdminLogin {
	
	@Autowired
	private AdminRepository aRepo;

	@GetMapping("/adminLogin")
	public String loginPage()
	{
		return "adminLogin";
	}
	
	@PostMapping("/adminLogin")
public String postLoginPage(@ModelAttribute Admin ar, Model m, HttpSession session)
	{
		String username= ar.getUsername();
		String password= ar.getPassword();
		String hashPassword =DigestUtils.md5Hex(password.getBytes());
		
		boolean result=	aRepo.existsByUsernameAndPassword(username, hashPassword);
		if(result==true)
		{
			session.setAttribute("activeAdmin", username);
			session.setMaxInactiveInterval(600);
				return "admin";
		}
		else
		{
		m.addAttribute("loginerror","Username or Password Incorrect");
			return "adminLogin";
		}
	}



}
