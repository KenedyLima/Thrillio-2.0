
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
@RequestMapping("/")
public class MainController {

	@RequestMapping(method=RequestMethod.GET)
	public String getLandingPage() {
		return "landing-page";
	}
	
	@RequestMapping(method=RequestMethod.GET, value = "/home")
	public String getHomePage() {
		return "home-page";
	}

	@RequestMapping(method=RequestMethod.GET, value = "/browse")
	public String getBrowsePage() {
		return "browse-page";
	}
	
	@RequestMapping(method=RequestMethod.GET, value = "/bookmarks")
	public String getBookmarksPage() {
		return "bookmarks-page";
	}
}
