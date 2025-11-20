package com.cts.webmvcdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

	@GetMapping({ "", "/", "/home", "/index" })
	public String showHomePageAction() {
		return "home";
	}
}
