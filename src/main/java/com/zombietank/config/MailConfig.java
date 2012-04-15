package com.zombietank.config;

import java.util.Properties;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.zombietank.config.annotation.Dev;
import com.zombietank.config.annotation.Prod;
import com.zombietank.email.Email;
import com.zombietank.email.EmailService;
import com.zombietank.email.exception.EmailException;
import com.zombietank.email.support.SimpleEmailService;

@Configuration
public class MailConfig {

	@Dev @Configuration
	static class Development {
		@Bean
		public EmailService emailService() {
			return new LoggingEmailService();
		}

		/***
		 * Logs email bean at info level, but does not actually send anything.
		 */
		private static class LoggingEmailService implements EmailService {
			private static final Logger logger = LoggerFactory.getLogger(LoggingEmailService.class);

			@Override
			public void send(Email email) throws EmailException {
				logger.info("Sending email: " + email);
			}
		}
	}

	@Prod @Configuration
	static class Production {
		@Inject private Environment environment;
		
		@Bean
		public EmailService emailService() {
			return new SimpleEmailService(mailSender());
		}
		
		@Bean
		public JavaMailSender mailSender() {
			JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
			mailSender.setDefaultEncoding(environment.getProperty("mail.encoding", "UTF-8"));
			mailSender.setHost(environment.getProperty("mail.host", "localhost"));
			mailSender.setPort(environment.getProperty("mail.port", Integer.class, 25));
			mailSender.setUsername(environment.getProperty("mail.username"));
			mailSender.setPassword(environment.getProperty("mail.password"));
			Properties properties = new Properties();
			properties.put("mail.smtp.auth", environment.getProperty("mail.smtp.auth", Boolean.class, false));
			properties.put("mail.smtp.starttls.enable", environment.getProperty("mail.smtp.starttls.enable", Boolean.class, false));
			mailSender.setJavaMailProperties(properties);
			return mailSender;
		}
	}
}