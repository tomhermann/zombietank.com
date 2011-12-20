package com.zombietank.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/secure/panda")
public class AdminController {

	@RequestMapping(method=RequestMethod.GET) 
	public String getForm() {
		return "admin";
	}
}
