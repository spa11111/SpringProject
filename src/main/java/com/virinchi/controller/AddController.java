package com.virinchi.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.virinchi.model.Story;
import com.virinchi.repository.StoryRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class AddController {

	@Autowired
	StoryRepository storyrepo;
	
	@GetMapping("/new")
    public String gallery(Model m, HttpSession session) {
		if(session.getAttribute("activeUser") != null) {
		m.addAttribute("imageList",storyrepo.findAll());
		        return "addBook";
		}
		else {
		    return "index";
		}

    }
	
	@PostMapping("/new")
    public String galleryPost(@RequestParam("image") MultipartFile image, Model m)
    {
        try {
            byte[] imgBytes= image.getBytes();
			 Story imgObj= new Story();
			 String imgString= Base64.getEncoder().encodeToString(imgBytes);
			 //Sets the image data after its encoded in string
			 imgObj.setImage(imgString);
			
			 storyrepo.save(imgObj);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        m.addAttribute("imageList",storyrepo.findAll());
        return "userPage";
    }
	
	
}
