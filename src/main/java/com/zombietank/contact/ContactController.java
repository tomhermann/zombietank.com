package com.zombietank.contact;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zombietank.email.exception.EmailException;

@Controller
@RequestMapping("/contact")
public class ContactController {
	@Inject
	private ContactService contactService;

	@RequestMapping(method = RequestMethod.GET)
	public ContactForm getNewForm() {
		return new ContactForm();
	}

	@RequestMapping(method = RequestMethod.POST)
	public String submit(@Valid ContactForm message, BindingResult result, RedirectAttributes redirectAttributes) throws EmailException {
		if (result.hasErrors()) {
			return "contact";
		}
		contactService.process(message);
		redirectAttributes.addFlashAttribute("success", "Message has been sent!");
		return "redirect:/contact";
	}

}
