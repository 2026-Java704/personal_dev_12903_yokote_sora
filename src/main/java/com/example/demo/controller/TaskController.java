package com.example.demo.controller;

import java.sql.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TaskController {

	@GetMapping("/records/add")
	public String index() {
		return "Records";
	}

	@PostMapping("/past")
	public String enter(
			@RequestParam Date date,
			@RequestParam String name,
			@RequestParam Integer time,
			@RequestParam Integer weight,
			@RequestParam Integer mets) {

		return null;
	}
}