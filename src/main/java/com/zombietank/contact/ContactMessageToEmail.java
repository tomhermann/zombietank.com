package com.zombietank.contact;

import javax.inject.Inject;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.google.common.base.Function;
import com.zombietank.email.Email;

@Component
public class ContactMessageToEmail implements Function<ContactMessage, Email >{
	@Inject 
	private Environment environment;
	
	@Override
	public Email apply(ContactMessage message) {
		return new Email().to(environment.getProperty("contact.email"), 
							  environment.getProperty("contact.name"))
						  .from(message.getEmail(), message.getName())
						  .withSubject(message.getSubject())
						  .withMessage(message.getMessage());
	}
}
