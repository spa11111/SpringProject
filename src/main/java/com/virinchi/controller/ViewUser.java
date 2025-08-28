package com.virinchi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.virinchi.model.User;
import com.virinchi.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class ViewUser {
	
	@Autowired
	private UserRepository urepo;
	@GetMapping("/viewuser")
	private String user(Model m, HttpSession session) {
		if(session.getAttribute("activeUser") != null) {
			List<User> uList= urepo.findAll();
			m.addAttribute("uList", uList);
			return "viewuser";
		}
		else
        {
            return "index";
        }

	}

}
