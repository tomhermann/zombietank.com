package com.zombietank.contact;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.zombietank.email.EmailService;
import com.zombietank.email.exception.EmailException;

@Service
public class ContactService {
	@Inject private EmailService emailService;
	@Inject private ContactMessageToEmail contactMessageToEmail;
	@Inject private ContactMessageRepository messageRepository;

	public void process(ContactMessage message) throws EmailException {
		messageRepository.save(message);
		emailService.send(contactMessageToEmail.apply(message));
	}
}
