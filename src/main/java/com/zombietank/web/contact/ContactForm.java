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
	
	@NotBlank
	private String name;

	@Email
	@NotBlank
	private String email;

	@NotBlank
	@Size(max = 150)
	private String subject;
	
	@NotBlank
	private String message;
}
