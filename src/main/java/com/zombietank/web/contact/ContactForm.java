package com.zombietank.web.contact;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
public class ContactForm implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message="Please provide your name.")
	private String name;

	@Email(message="Please provide a valid email address.")
	@NotBlank(message="Please provide your email address.")
	private String email;

	@NotBlank(message="Please provide a subject for your message.")
	@Size(message="The subject cannot exceed 150 characters", max = 150)
	private String subject;
	
	@NotBlank(message="Please provide a message.")
	private String message;
}
