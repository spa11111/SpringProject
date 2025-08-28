package com.virinchi.controller;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.virinchi.model.Admin;
import com.virinchi.repository.AdminRepository;


@Controller
public class AdminRegister {

	@Autowired 
	private AdminRepository aRepo;

	@GetMapping("/adminRegister")
	public String signupPage()
	{
		return "adminRegister";
	}
	
	@PostMapping("/adminRegister")
	public String signupPagePost(@ModelAttribute Admin ar)
	{

		
		String username=ar.getUsername();
		String password=ar.getPassword();
		String hashPassword= DigestUtils.md5Hex(password.getBytes());
		
		ar.setPassword(hashPassword);
		aRepo.save(ar);
		
		return "adminLogin";
	}
	
}
