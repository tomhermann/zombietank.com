package com.zombietank.web.contact;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;

@RunWith(MockitoJUnitRunner.class)
public class ContactControllerTest {
	@InjectMocks private ContactController contactController = new ContactController();
	@Mock private ContactService contactService;
	
	@Test
	public void getNewFormReturnsNewFormInstance() {
		assertThat(contactController.getNewForm(), is(notNullValue()));
	}

	@Test
	public void whenThereAreFormErrorsReturnToInput() {
		ContactForm message = new ContactForm();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		
		String result = contactController.submit(message, bindingResult);
		
		assertThat(result, is(equalTo("contact")));
	}

	@Test
	public void whenThereAreNoErrorsProcessMessage() {
		ContactForm message = new ContactForm();
		
		String result = contactController.submit(message, mock(BindingResult.class));
		
		assertThat(result, is(equalTo("redirect:/contact")));
		verify(contactService).process(message);
	}
}
