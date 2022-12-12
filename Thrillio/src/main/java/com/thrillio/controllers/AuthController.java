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

import com.thrillio.entities.User;
import com.thrillio.repositories.UserRepository;

@Controller
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserRepository repository;

	@RequestMapping("/signUp")
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
		System.out.println("Submit");
		System.out.println("Has erros?: " + br.hasErrors());
		System.out.println(user);
		if(br.hasErrors()) return "sign-up-page";
		repository.save(user);
		return "home-page";
	}
	

	@GetMapping("/user")
	public String getUserInformation(Principal principal, Model model) {
		User user = repository.findByEmail(principal.getName());
		model.addAttribute("user", user);
		return "configuration-page"	;
	}
}
