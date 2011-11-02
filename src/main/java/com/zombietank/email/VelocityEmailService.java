package com.zombietank.email;

import static com.zombietank.email.EmailPreparator.preparable;
import static org.springframework.ui.velocity.VelocityEngineUtils.mergeTemplateIntoString;

import java.io.Serializable;
import java.util.Map;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;

import com.zombietank.email.support.AbstractEmailService;

public class VelocityEmailService extends AbstractEmailService {
	private static final Logger log = LoggerFactory.getLogger(VelocityEmailService.class);
	private final JavaMailSender mailSender;
	private final VelocityEngine velocityEngine;

	public VelocityEmailService(JavaMailSender mailSender, VelocityEngine velocityEngine) {
		this.mailSender = mailSender;
		this.velocityEngine = velocityEngine;
	}
	
	@Async
	public void send(Email email) {
		log.info("Sending email to: {}", email.getTo());
		log.debug("Sending: {}", email);
	
		try {
			mailSender.send(preparable(email));
			log.info("Sent email to: {}", email.getTo());
		} catch(Exception e) {
			log.error("Failed: email not sent to: {}", email.getTo(), e);
		}
	}
	
	public void send(Email email, String templateLocation, Map<String, ? extends Serializable> model) {
		log.debug("Using templateLocation: {}", templateLocation);
		log.debug("Using model: {}", model);

		try {
			email.setMessage(mergeTemplateIntoString(velocityEngine, templateLocation, model));
			send(email);
		} catch(Exception e) {
			log.error("Failed: email not sent to: {}", email.getTo(), e);
		}
	}
}
