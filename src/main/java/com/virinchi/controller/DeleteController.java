package com.virinchi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.virinchi.service.StoryService;
import com.virinchi.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class DeleteController {
	
    @Autowired
    private StoryService storyService;
    
    @Autowired
    private UserService userService;

    // Delete story by ID
    @GetMapping("/story/delete/{id}")
    public String deleteStory(@PathVariable("id") long id, HttpSession session) {
        // Optional: check if user is logged in
        if (session.getAttribute("activeUser") == null) {
            return "redirect:/index"; // redirect if not logged in
        }

        // Call service to delete
        storyService.deleteStory(id);

        // Redirect to some page (e.g., list of stories)
        return "redirect:/userPage"; 
    }
    
    @GetMapping("/user/del/{id}")
    public String deleteUser(@PathVariable("id") int id, HttpSession session) {
        if (session.getAttribute("activeAdmin") == null) {
            return "redirect:/index";
        }
        userService.deleteUser(id);
        return "redirect:/viewuser";
    }


}
