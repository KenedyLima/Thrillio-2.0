
package com.thrillio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class MainController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String getLandingPage() {
		return "homepage";
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
