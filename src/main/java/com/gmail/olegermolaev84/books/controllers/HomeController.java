package com.gmail.olegermolaev84.books.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HomeController {
	
	@GetMapping(value = {"/", "/index"})
	public String homePage() {
		return "home";
	}
}
