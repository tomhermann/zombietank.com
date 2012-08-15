package com.zombietank.contact;

import java.util.Collection;

import javax.inject.Inject;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Secured("ROLE_SUPERVISOR")
@RequestMapping("/contact-messages")
public class ContactMessageController {
	@Inject
	private ContactMessageRepository messageRepository;

	@RequestMapping("all")
	@ResponseBody
	public Collection<ContactMessage> showAllMessages() {
		return messageRepository.getAllMessages();
	}
	
	@RequestMapping("message/{id}")
	@ResponseBody
	public ContactMessage showMessage(@PathVariable long id) {
		return messageRepository.getMessage(id);
	}
}
