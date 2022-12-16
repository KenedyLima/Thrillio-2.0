package com.thrillio.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.thrillio.entities.User;
import com.thrillio.repositories.UserRepository;

@Controller
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserRepository repository;
	private User user;

	@RequestMapping("/register")
	public String getSignUpForm(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "sign-up-page";
	}

	@RequestMapping("/login")
	public String getSignInForm(Model model) {
		model.addAttribute("user", new User());
		return "sign-in-page";
	}

	@PostMapping("/perform_registration")
	public String submitSignUpForm(@RequestBody User user, BindingResult br) {
		System.out.println(user);
		if (br.hasErrors())
			return "sign-up-page";
		repository.save(user);

		return "bookmarks-page";
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/user")
	public String updateUserInfo(Model model, Principal principal) {
		System.out.println("Not different");
		System.out.println("UpdateUser");
		model.addAttribute("user", new User());
		return "configuration-page";
		
	}

	@GetMapping("/user")
	public String getUser(Model model, Principal principal) {
		System.out.println("GetUser");
		User user =repository.findByEmail("user");
		model.addAttribute("user", user);
		return "configuration-page";
	}

}
