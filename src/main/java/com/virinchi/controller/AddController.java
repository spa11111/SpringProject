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
import com.virinchi.repository.StoryRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class AddController {

	@Autowired
	StoryRepository storyrepo;
	
	@GetMapping("/new")
    public String Books(Model m, HttpSession session) {
		if(session.getAttribute("activeUser") != null) {
			List<Story> story =storyrepo.findAll();
				m.addAttribute("imageList",story);
		        return "addBook";		 
		}
		else {
		    return "index";
		}

    }
	
	@PostMapping("/new")
    public String newGamePost(@ModelAttribute Story story) {
        try {
            if (story.getFile() != null && !story.getFile().isEmpty()) {
                byte[] imageBytes = story.getFile().getBytes();
                String imgString = Base64.getEncoder().encodeToString(imageBytes);
                story.setImage(imgString);
            }
            storyrepo.save(story);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "userPage";   // redirect where you want after save
    }





	   
	
	
}
