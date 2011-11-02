package com.zombietank.web.contact;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/contact")
public class ContactController {
	@Autowired
	private ContactService contactService;

	@RequestMapping(method = RequestMethod.GET)
	public ContactForm getNewForm() {
		return new ContactForm();
	}

	@RequestMapping(method = RequestMethod.POST)
	public String submit(@Valid ContactForm message, BindingResult result) {
		if (result.hasErrors()) {
			return "contact";
		}
		contactService.process(message);
		return "redirect:/contact";
	}

}
