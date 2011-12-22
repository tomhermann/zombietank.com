package com.zombietank.configure;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Secured("ROLE_SUPERVISOR")
@RequestMapping("/configure-me")
public class AdminController {
	
	@RequestMapping(method=RequestMethod.GET) 
	public String getForm() {
		return "admin";
	}
}
