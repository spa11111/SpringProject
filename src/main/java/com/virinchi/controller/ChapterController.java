package com.virinchi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.virinchi.model.Story;
import com.virinchi.service.StoryService;
import jakarta.servlet.http.HttpSession;

@Controller
public class ChapterController {

    private final StoryService storyService;

    public ChapterController(StoryService storyService) {
        this.storyService = storyService;
    }

    // Show the "readBook" page
    @GetMapping("/read/{id}")
    public String readBook(@PathVariable Long id, HttpSession session, Model model) {
        if (session.getAttribute("activeUser") == null) {
            return "index"; // redirect to login if not logged in
        }
        Story story = storyService.getStory(id);
        model.addAttribute("story", story);
        return "readBook"; // must match readBook.html
    }

    // Handle voting (POST request)
    @PostMapping("/vote/{id}")
    public String voteStory(@PathVariable Long id, HttpSession session) {
        if (session.getAttribute("activeUser") == null) {
            return "index"; // redirect to login if not logged in
        }
        storyService.vote(id);
        return "redirect:/read/" + id; // reload the same page with updated votes
    }

}
