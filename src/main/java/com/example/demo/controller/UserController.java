package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.User;

@Controller
public class UserController {
	private final HttpSession session;
	private final User user;

	public UserController(HttpSession session, User user) {
		this.session = session;
		this.user = user;
	}

	@GetMapping({ "/", "/login", "/logout" })
	public String index() {
		session.invalidate();
		return "login";
	}
}
