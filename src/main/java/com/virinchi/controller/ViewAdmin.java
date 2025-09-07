package com.virinchi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.virinchi.model.Admin;
import com.virinchi.model.User;
import com.virinchi.repository.AdminRepository;
import com.virinchi.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class ViewAdmin {
	
	@Autowired
	private AdminRepository arepo;
	@GetMapping("/viewadmin")
	private String Admins(Model m, HttpSession session) {
		if(session.getAttribute("activeAdmin") != null) {
			List<Admin> aList= arepo.findAll();
			m.addAttribute("aList", aList);
			return "viewAdmin";
		}
		else {
		    return "adminLogin";
		}

	}
	

}
