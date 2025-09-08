package com.virinchi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.virinchi.model.Story;
import com.virinchi.model.User;
import com.virinchi.repository.StoryRepository;
import com.virinchi.repository.UserRepository;
import com.virinchi.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserPageController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private StoryRepository storyrepo;


    @GetMapping("/userPage")
    public String userPage(Model model, HttpSession session) {
        String username = (String) session.getAttribute("activeUser");  // get username
        if (username != null) {
            User activeUser = userService.findByUsername(username);  // fetch full User from DB
            List<Story> publishedStories = storyrepo.findByUserAndPublishedTrue(activeUser);

            model.addAttribute("user", activeUser);
            model.addAttribute("stories", publishedStories);
            return "userPage";
        }
        return "index"; // if not logged in
    }



}
