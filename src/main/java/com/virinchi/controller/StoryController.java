package com.virinchi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.virinchi.model.Story;
import com.virinchi.model.User;
import com.virinchi.service.StoryService;

import jakarta.servlet.http.HttpSession;

@Controller
public class StoryController {

    @Autowired
    private StoryService storyService;



    
    @PostMapping("/stories/{id}/vote")
    public String voteStory(@PathVariable Long id) {
        storyService.vote(id);
        return "redirect:/read/" + id;
    }

    @GetMapping("/stories")
    public String listStories(Model model) {
        List<Story> stories = storyService.getAllStories();
        model.addAttribute("stories", stories);
        return "Stories"; // must exactly match Stories.html
    }

}

