package com.zombietank.config;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.Properties;

import javax.inject.Inject;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;

import com.zombietank.config.annotation.Dev;
import com.zombietank.config.annotation.Prod;
import com.zombietank.email.Email;
import com.zombietank.email.EmailService;
import com.zombietank.email.VelocityEmailService;
import com.zombietank.email.support.AbstractEmailService;

@Configuration
public class MailConfig {

	@Bean
	public VelocityEngine velocityEngine() throws IOException {
		VelocityEngineFactoryBean velocityEngineFactory = new VelocityEngineFactoryBean();
		Properties properties = new Properties();
		properties.put("resource.loader", "class");
		properties.put("class.resource.loader.class", ClasspathResourceLoader.class.getName());
		velocityEngineFactory.setVelocityProperties(properties);
		return velocityEngineFactory.createVelocityEngine();
	}

	@Dev @Configuration 
	static class Development {
		/***
		 * Prints rendered e-mails to System.out.
		 */
		@Bean
		public EmailService emailService() {
			return new AbstractEmailService() {
				public void send(Email email) {
					System.out.println("e-mail: " + email);
				}
				
				public void send(Email email, String templateLocation, Map<String, ? extends Serializable> model) {
					System.out.println("templateLocation: " + templateLocation);
					System.out.println("model: " + model);
					send(email);
				}
			};
		}
	}

	@Prod @Configuration
	static class Production {
		@Inject private VelocityEngine velocityEngine;
		@Inject private Environment environment;
		
		@Bean
		public EmailService emailService() {
			return new VelocityEmailService(mailSender(), velocityEngine);
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