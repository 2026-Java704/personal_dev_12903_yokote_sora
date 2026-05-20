package com.example.demo.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Exercise;
import com.example.demo.repository.ExerciseRepository;

@Controller
public class TaskController {
	ExerciseRepository exerciseRepository;

	public TaskController(ExerciseRepository exersiseReposiory) {
		this.exerciseRepository = exerciseRepository;
	}

	//入力フォーム表示
	@GetMapping("/records/add")
	public String index() {
		return "Records";
	}

	//入力処理
	@PostMapping("/past")
	public String enter(
			@RequestParam Date date,
			@RequestParam String name,
			@RequestParam Integer time,
			@RequestParam Integer weight,
			@RequestParam Integer mets,
			Model model) {
		List<String> errorList = new ArrayList<>();
		if (time == null) {
			errorList.add("時間は必須です");
		}
		if (weight == null) {
			errorList.add("体重は必須です");
		}

		if (errorList.size() > 0) {
			model.addAttribute("errorList", errorList);
			model.addAttribute("time", time);
			model.addAttribute("weight", weight);
			return "/Records";
		}
		Exercise exercise = new Exercise(time, date, weight);
		exerciseRepository.save(exercise);

		return null;
	}

	@GetMapping("/memory")
	public String memoty() {
		return "";
	}
}