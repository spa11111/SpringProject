package com.virinchi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.virinchi.model.Story;
import com.virinchi.model.User;
import com.virinchi.service.StoryService;
import com.virinchi.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PremiumController {

    @Autowired
    private StoryService storyService;

    @Autowired
    private UserService userService;

    // Show book description page    
    @GetMapping("/premiumBook/{id}")
    public String premiumStory(@PathVariable long id, HttpSession session, Model model) {
        // maintain your session check style
        if (session.getAttribute("activeUser") == null) {
            return "index"; // redirect if not logged in
        }

        String username = (String) session.getAttribute("activeUser");
        User activeUser = userService.findByUsername(username);
        if (activeUser == null) {
            return "index"; // user not found
        }

        Story story = storyService.findById(id);
        if (story == null) {
            return "index"; // story not found
        }

        model.addAttribute("story", story);
        return "bookDescription";
    }

    // Deduct coins and allow reading
    @GetMapping("/premium/read/{id}")
    public String readStory(@PathVariable long id, Model m, HttpSession session) {
        // same session style
        if (session.getAttribute("activeUser") == null) {
            return "index"; // redirect if not logged in
        }

        String username = (String) session.getAttribute("activeUser");
        User activeUser = userService.findByUsername(username);
        if (activeUser == null) {
            return "index"; // user not found
        }

        Story story = storyService.findById(id);
        if (story == null) {
            return "index"; // story not found
        }

        int requiredCoins = story.getPart();

        if (activeUser.getCoins() >= requiredCoins) {
            // Deduct coins
            activeUser.setCoins(activeUser.getCoins() - requiredCoins);
            userService.updateUser(activeUser);

            // session still stores username
            session.setAttribute("activeUser", username);

            m.addAttribute("story", story);
            return "readChap"; // show reading page
        } else {
            m.addAttribute("coinerror", "Not enough coins to read this story!");
            m.addAttribute("story", story);
            return "bookDescription"; // back to description
        }
    }
    
    
    // Deduct coins and allow reading
    @GetMapping("/premium/reads/{id}")
    public String readsStory(@PathVariable long id, Model m, HttpSession session) {
        // same session style
        if (session.getAttribute("activeUser") == null) {
            return "index"; // redirect if not logged in
        }

        String username = (String) session.getAttribute("activeUser");
        User activeUser = userService.findByUsername(username);
        if (activeUser == null) {
            return "index"; // user not found
        }

        Story story = storyService.findById(id);
        if (story == null) {
            return "index"; // story not found
        }

        int requiredCoins = story.getCoins();

        if (activeUser.getCoins() >= requiredCoins) {
            // Deduct coins
            activeUser.setCoins(activeUser.getCoins() - requiredCoins);
            userService.updateUser(activeUser);

            // session still stores username
            session.setAttribute("activeUser", username);

            m.addAttribute("story", story);
            return "readChap"; // show reading page
        } else {
            m.addAttribute("coinerror", "Not enough coins to read this story!");
            m.addAttribute("story", story);
            return "bookDescription"; // back to description
        }
    }
    
    
    // Handle voting (POST request)
    @PostMapping("/voted/{id}")
    public String voteStory(@PathVariable Long id, HttpSession session) {
        if (session.getAttribute("activeUser") == null) {
            return "index"; // redirect to login if not logged in
        }
        storyService.vote(id);
        return "redirect:/premium/read/" + id; // reload the same page with updated votes
    }
    
    
}
