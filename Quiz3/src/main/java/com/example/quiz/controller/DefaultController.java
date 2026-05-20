package com.example.quiz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

	@GetMapping("/")
	public String defaultHandlerMethod() {

		return "redirect:/quiz";
		// return "redirect:/h2-console";

	}

}
