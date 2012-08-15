package com.zombietank.contact;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.google.common.base.Objects;

@Entity
@Table(name="contact_message")
public class ContactMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id;

	@NotBlank(message = "Please provide your name.")
	private String name;

	@Email(message = "Please provide a valid email address.")
	@NotBlank(message = "Please provide your email address.")
	private String email;

	@NotBlank(message = "Please provide a subject for your message.")
	@Size(message = "The subject cannot exceed 150 characters", max = 150)
	private String subject;

	@NotBlank(message = "Please provide a message.")
	private String message;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String toString() {
		return Objects.toStringHelper(this)
				.add("id", id)
				.add("email", email)
				.add("message", message)
				.add("name", name)
				.add("subject", subject)
			.toString();
	}
}
