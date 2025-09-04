package com.virinchi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.virinchi.model.Story;
import com.virinchi.service.StoryService;
import jakarta.servlet.http.HttpSession;

@Controller
public class ChapterController {

    private final StoryService storyService;

    public ChapterController(StoryService storyService) {
        this.storyService = storyService;
    }

    @GetMapping("/read/{id}")
    public String readBook(@PathVariable Long id, HttpSession session, Model model) {
        if (session.getAttribute("activeUser") == null) {
            return "index";
        }
        Story story = storyService.getStory(id);
        model.addAttribute("story", story);
        return "readBook"; // must match readBook.html
    }
}
