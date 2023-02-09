package com.codeinteracts.moviereview.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web/login")
public class LoginController {
	
	@GetMapping("/login")
	public String list(Model model) {
//		String message = (String) model.asMap().get("successMessage");
		
		return "login/login";
	}

}
