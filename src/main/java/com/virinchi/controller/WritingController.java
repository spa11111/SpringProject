package com.virinchi.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.virinchi.model.Chapter;
import com.virinchi.model.Story;
import com.virinchi.model.User;
import com.virinchi.repository.ChapterRepository;
import com.virinchi.repository.StoryRepository;
import com.virinchi.service.ChapterService;
import com.virinchi.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class WritingController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	ChapterRepository chapterRepo;
	
	@Autowired
	ChapterService chapterService;
	
	@Autowired
	StoryRepository storyrepo;
	

	@GetMapping("/writing/{id}")
	public String writingPage(@PathVariable Long id, Model model) {
	    Story story = storyrepo.findById(id)
	            .orElseThrow(() -> new RuntimeException("Story not found"));
	    model.addAttribute("story", story);   // ✅ required for ${story.id}
	    return "writing";
	}


	
	
	@PostMapping("/publishChapter")
	public String publishChapter(@RequestParam String title,
	                             @RequestParam String content,
	                             @RequestParam Long storyId,  // get story id from form
	                             HttpSession session) {
	    String username = (String) session.getAttribute("activeUser");
	    if (username == null) {
	        return "redirect:/login";
	    }

	    User user = userService.findByUsername(username);
	    Story story = storyrepo.findById(storyId).orElse(null);

	    if (story == null) {
	        return "redirect:/userPage"; // story not found
	    }

	    // Create a new chapter
	    Chapter chapter = new Chapter();
	    chapter.setTitle(title);
	    chapter.setContent(content);
	    chapter.setStory(story);

	    chapterRepo.save(chapter);

	    // Mark story as published
	    story.setPublished(true);
	    storyrepo.save(story);

	    // Increment works count
	    user.incrementWork();
	    
	 // Increment coins (for example +10 coins per published chapter)
	    user.setCoins(user.getCoins() + 5);
	    userService.save(user);

	    return "redirect:/userPage";
	}






}
	
