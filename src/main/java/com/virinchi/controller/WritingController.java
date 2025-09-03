package com.virinchi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WritingController {

		@GetMapping("/write")
		public String WriteBook() {
			return "writing";
		}

}
	
