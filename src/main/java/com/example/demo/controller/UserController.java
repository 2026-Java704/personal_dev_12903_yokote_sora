package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.User;
import com.example.demo.model.Account;
import com.example.demo.repository.UsersRepository;

@Controller
public class UserController {
	private final Account account;
	private final HttpSession session;
	private final UsersRepository usersRepository;

	public UserController(
			HttpSession session,
			Account account,
			UsersRepository usersRepository) {
		this.session = session;
		this.account = account;
		this.usersRepository = usersRepository;
	}

	//ログイン画面表示
	@GetMapping({ "/", "/login", "/logout" })
	public String index() {
		session.invalidate();
		return "login";
	}

	//ログイン処理
	@PostMapping("/login")
	public String login(
			@RequestParam String name,
			@RequestParam String password,
			Model model) {
		// 名前が空の場合にエラーとする
		if (name.length() == 0 || password.length() == 0) {
			model.addAttribute("message", "入力してください");
			return "login";
		}
		List<User> userList = usersRepository.findByNameAndPassword(name, password);
		if (userList == null || userList.size() == 0) {
			model.addAttribute("message", "名前とパスワードが一致しませんでした");
			return "login";
		}
		account.setName(name);
		return "redirect:/records/add";
	}

	//新規会員登録画面表示
	@GetMapping("/users/add")
	public String create(Model model) {
		return "addUser";
	}

	//新規会員登録処理
	@PostMapping("/users/add")
	public String add(
			@RequestParam(defaultValue = "") String name,
			@RequestParam(defaultValue = "") String password,
			Model model) {
		List<String> errorList = new ArrayList<>();
		if ("".equals(name)) {
			errorList.add("名前は必須です");
		}
		if ("".equals(password)) {
			errorList.add("パスワードは必須です");
		}
		if (errorList.size() > 0) {
			model.addAttribute("errorList", errorList);
			model.addAttribute("name", name);
			model.addAttribute(password);
			return "/login";
		}
		User user = new User(name, password);
		usersRepository.save(user);
		return "redirect:/login";
	}
}
