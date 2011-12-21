package com.zombietank.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/login")
public class LoginAction {

	@RequestMapping(method=RequestMethod.GET)
	public String loginForm() {
		System.out.println("Fire weapons!");
		return "login";
	}
}
