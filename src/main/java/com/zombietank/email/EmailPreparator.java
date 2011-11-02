package com.zombietank.email;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map.Entry;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

public class EmailPreparator implements MimeMessagePreparator, Serializable {
	private static final long serialVersionUID = 1L;
	private final Email email;

	public static EmailPreparator preparable(final Email email)  {
		return new EmailPreparator(email);
	}
	
	public EmailPreparator(final Email email) {
		this.email = email;
	}

	public void prepare(final MimeMessage mimeMessage) throws Exception {
		MimeMessageHelper message = new MimeMessageHelper(mimeMessage, email.hasAttachments());
		message.setTo(toArray(email.getTo()));
		message.setCc(toArray(email.getCc()));
		message.setBcc(toArray(email.getBcc()));
		message.setFrom(email.getFrom());
		message.setText(email.getMessage(), email.isHtml());
		message.setPriority(email.getPriority().getValue());
		
		if(email.hasReplyTo()) {
			message.setReplyTo(email.getReplyTo());
		}
		
		if(email.hasAttachments()) {
			for(Resource attachment: email.getAttachments()) {
				message.addAttachment(attachment.getFilename(), attachment.getFile());
			}
		}
		
		if(!email.getHeaders().isEmpty()) {
			for (Entry<String, String> entry : email.getHeaders().entrySet()) {
				mimeMessage.addHeader(entry.getKey(), entry.getValue());
			}
		}
	}
	
	private static InternetAddress[] toArray(final Collection<InternetAddress> input) {
		return input.toArray(new InternetAddress[input.size()]);
	}
}
