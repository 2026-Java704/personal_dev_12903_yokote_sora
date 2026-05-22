package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Events;
import com.example.demo.entity.User;
import com.example.demo.model.Account;
import com.example.demo.repository.EventsRepository;
import com.example.demo.repository.UsersRepository;

@Controller
public class UserController {

	private HttpSession session = null;
	private Account account = new Account();
	private UsersRepository usersRepository = null;
	private EventsRepository eventsRepository = null;

	public UserController(HttpSession session, Account account, UsersRepository usersRepository,
			EventsRepository eventsRepository) {
		this.session = session;
		this.account = account;
		this.usersRepository = usersRepository;
		this.eventsRepository = eventsRepository;
	}

	//ログイン画面表示
	@GetMapping({ "/", "/login", "/logout" })
	public String index() {
		session.invalidate();
		return "login";
	}

	//ログアウト処理
	@PostMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/login";
	}

	//ログイン処理
	@PostMapping("/login")
	public String login(@RequestParam String name, @RequestParam String password, Model model) {
		String trimmedName = name.trim();
		if (trimmedName.isEmpty() || password.isEmpty()) {
			model.addAttribute("message", "入力してください");
			model.addAttribute("name", trimmedName);
			return "login";
		}

		List<User> userList = usersRepository.findByNameAndPassword(trimmedName, password);
		if (userList.isEmpty()) {
			model.addAttribute("message", "名前とパスワードが一致しませんでした");
			model.addAttribute("name", trimmedName);
			return "login";
		}

		User user = userList.get(0);
		account.setId(user.getId());
		account.setName(user.getName());

		return "redirect:/records/add";
	}

	//新規登録画面表示
	@GetMapping("/users/new")
	public String create() {
		return "users";
	}

	//新規登録処理
	@PostMapping("/users/add")
	public String add(
			@RequestParam String name,
			@RequestParam String password,
			Model model) {
		String trimmedName = name.trim();
		List<String> errorList = new ArrayList<>();
		if (trimmedName.isEmpty()) {
			errorList.add("名前は必須です");
		}
		if (password.isEmpty()) {
			errorList.add("パスワードは必須です");
		}
		if (!trimmedName.isEmpty() && usersRepository.existsByName(trimmedName)) {
			errorList.add("その名前は既に登録されています");
		}

		if (!errorList.isEmpty()) {
			model.addAttribute("errorList", errorList);
			model.addAttribute("name", trimmedName);
			return "users";
		}

		User user = new User(trimmedName, password);
		usersRepository.save(user);
		addDefaultEvents(user.getId());

		return "redirect:/login";
	}

	//
	private void addDefaultEvents(Integer userId) {
		Events event1 = new Events();
		event1.setUserId(userId);
		event1.setName("スクワット");
		event1.setMets(5.0);
		eventsRepository.save(event1);

		Events event2 = new Events();
		event2.setUserId(userId);
		event2.setName("縄跳び");
		event2.setMets(9.0);
		eventsRepository.save(event2);

		Events event3 = new Events();
		event3.setUserId(userId);
		event3.setName("ブルガリアンスクワット");
		event3.setMets(7.0);
		eventsRepository.save(event3);

		Events event4 = new Events();
		event4.setUserId(userId);
		event4.setName("プランク");
		event4.setMets(4.0);
		eventsRepository.save(event4);

		Events event5 = new Events();
		event5.setUserId(userId);
		event5.setName("腕立て伏せ");
		event5.setMets(4.0);
		eventsRepository.save(event5);

		Events event6 = new Events();
		event6.setUserId(userId);
		event6.setName("腹筋");
		event6.setMets(4.0);
		eventsRepository.save(event6);

		Events event7 = new Events();
		event7.setUserId(userId);
		event7.setName("ランニング");
		event7.setMets(9.0);
		eventsRepository.save(event7);

		Events event8 = new Events();
		event8.setUserId(userId);
		event8.setName("ウォーキング");
		event8.setMets(4.0);
		eventsRepository.save(event8);

		Events event9 = new Events();
		event9.setUserId(userId);
		event9.setName("ピラティス");
		event9.setMets(3.0);
		eventsRepository.save(event9);
	}
}
