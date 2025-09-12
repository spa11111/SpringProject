package com.virinchi.controller;

import java.util.List;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.virinchi.model.Story;
import com.virinchi.model.User;
import com.virinchi.repository.StoryRepository;
import com.virinchi.service.StoryService;

import jakarta.servlet.http.HttpSession;

@Controller
public class StoryController {

	@Autowired
    private StoryRepository storyRepo;
	
	@Autowired
	private StoryService storyService;
	
    @GetMapping("/stories")
    public String listStories(Model model, HttpSession session) {
        // check if admin user is logged in
        if (session.getAttribute("activeAdmin") != null) {
            List<Story> stories = storyRepo.findAll();
            model.addAttribute("stories", stories);
            return "Stories"; // points to Stories.html
        }
        return "redirect:/adminLogin"; // if not admin, redirect to login
    }
    


}

