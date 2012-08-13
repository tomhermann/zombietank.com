package com.zombietank.contact;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.zombietank.email.Email;
import com.zombietank.email.EmailService;

@RunWith(MockitoJUnitRunner.class)
public class ContactServiceTest {
	@InjectMocks 
	private ContactService contactService;
	@Mock private EmailService emailService;
	@Mock private ContactMessageToEmail contactMessageToEmail;
	@Mock private ContactMessageRepository messageRepository;
	@Mock private ContactMessage message;

	@Test
	public void onProcessMessageIsSaved() throws Exception {
		contactService.process(message);
		
		verify(messageRepository).save(message);
	}

	@Test
	public void onProcessMessageIsSentAsEmail() throws Exception {
		Email email = new Email();
		when(contactMessageToEmail.apply(message)).thenReturn(email);
		
		contactService.process(message);

		verify(emailService).send(email);
	}

}
