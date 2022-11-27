package com.thrillio.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.thrillio.entities.User;
import com.thrillio.repositories.UserRepository;

@Controller
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(method=RequestMethod.GET)
	public String home() {
		return "home";
	}
	
	@RequestMapping("/signUp")
	public String getSignUpForm(Model model) {
		User user = new User();
		user.setUsername("Default");
		model.addAttribute("user", user);
		return "sign-up-page";
	}

	@RequestMapping("signUp/submit")
	public String getSignUpForm(@Valid @ModelAttribute("user") User user, BindingResult br) {
		System.out.println("Submit");
		System.out.println("Has erros?: " + br.hasErrors());
		System.out.println(user);
		if(br.hasErrors()) return "sign-up-page";
		return "home";
	}

	
	@PostMapping("/signIn")
	public String signInUser(@RequestParam String username, @RequestParam String password) {
		return "sign-in-page";
	}
	
	
}
