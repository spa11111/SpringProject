package com.virinchi.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.virinchi.model.Story;
import com.virinchi.model.User;
import com.virinchi.repository.StoryRepository;
import com.virinchi.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class AddController {

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	StoryRepository storyRepo;
	
	@GetMapping("/new")
    public String Books(Model m, HttpSession session) {
		if(session.getAttribute("activeUser") != null) {
			List<Story> story =storyRepo.findAll();
				m.addAttribute("imageList",story);
		        return "addBook";		 
		}
		else {
		    return "index";
		}

    }
	
	@PostMapping("/createDraft")
	public String createDraft(@ModelAttribute Story story,
	                          @RequestParam("file") MultipartFile file,
	                          HttpSession session) {
	    String username = (String) session.getAttribute("activeUser");
	    if (username == null) {
	        return "redirect:/login";
	    }

	    User user = userRepo.findByUsername(username);
	    story.setUser(user);
	    story.setPublished(false); // default: draft (not yet published)

	    // ✅ Handle file upload as Base64
	    if (file != null && !file.isEmpty()) {
	        try {
	            // Convert to Base64 string
	            String base64 = Base64.getEncoder().encodeToString(file.getBytes());
	            story.setImage(base64); // directly save as Base64 in DB
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    storyRepo.save(story);

	    // Redirect to writing page with story ID
	    return "redirect:/writing/" + story.getId();
	}










	   
	
	
}
