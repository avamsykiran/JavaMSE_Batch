package com.cts.webmvcdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

	/*
	 * A GET request to anu one these urls will invoke this action method
	 * http://localhost:9999
	 * http://localhost:9999/
	 * http://localhost:9999/home
	 * http://localhost:9999/index
	 * */
	@GetMapping({ "", "/", "/home", "/index" })
	public String showHomePageAction() {
		return "home"; // view is /templates/home.html
	}
}
