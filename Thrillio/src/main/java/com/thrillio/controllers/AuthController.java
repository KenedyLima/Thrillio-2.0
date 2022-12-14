package com.thrillio.controllers;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thrillio.entities.Authority;
import com.thrillio.entities.User;
import com.thrillio.repositories.UserRepository;

@Controller
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserRepository repository;

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
	
	@RequestMapping("/perform_registration")
	public String submitSignUpForm(@Valid @ModelAttribute("user") User user, BindingResult br) {
		if(br.hasErrors()) return "sign-up-page";
		Authority authority = new Authority(user);
		user.setAuthority(authority);
		user.setEnabled(true);
		repository.save(user);
		
		return "bookmarks-page";
	}
	

	@GetMapping("/user")
	public String getUserInformation(Principal principal, Model model) {
		if(principal == null) return "sign-in-page"; 
		System.out.println(principal.getName());
		User user = repository.findByEmail(principal.getName());
		model.addAttribute("user", user);
		return "configuration-page"	;
	}
}
