package com.virinchi.controller;

import com.virinchi.model.User;
import com.virinchi.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class IndexController {
/* 
 * Controller is just like servlet technology
 * It handles all the getter and setter functions
 * with  the help of HTTP protocol
 * 	
 */
	
	//COntroller provides - GetMapping, PostMapping
@Autowired
	private UserRepository uRepo;
	
@GetMapping("/")
public String myFirstPage() {
		return "index";
	}


	
@GetMapping("/home")
public String homePage(Model m,HttpSession session)
{
	if(session.getAttribute("activeUser") != null){
		List<User> uList= uRepo.findAll();
		m.addAttribute("uList", uList);
		return "home";
	}

	else {
		return "index";
	}
}
	
	

}
