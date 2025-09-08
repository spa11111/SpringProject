package com.virinchi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.virinchi.model.Story;
import com.virinchi.service.StoryService;

import jakarta.servlet.http.HttpSession;

@Controller
public class DescribeController {

    private final StoryService storyService;

    public DescribeController(StoryService storyService) {
        this.storyService = storyService;
    }

    @GetMapping("/normalBook/{id}")
    public String bookDescribe(@PathVariable Long id, HttpSession session, Model model) {
        if (session.getAttribute("activeUser") == null) {
            return "index";
        }
        Story story = storyService.getStory(id);
        model.addAttribute("story", story);
        return "bookDescribe"; // must match file name
    }

}
