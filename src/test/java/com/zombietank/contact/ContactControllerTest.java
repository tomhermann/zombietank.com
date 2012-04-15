package com.zombietank.contact;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zombietank.contact.ContactController;
import com.zombietank.contact.ContactForm;
import com.zombietank.contact.ContactService;

@RunWith(MockitoJUnitRunner.class)
public class ContactControllerTest {
	@InjectMocks private ContactController contactController = new ContactController();
	@Mock private ContactService contactService;
	@Mock private RedirectAttributes redirectAttributes;
	@Mock private BindingResult bindingResult;
	
	@Test
	public void getNewFormReturnsNewFormInstance() {
		assertThat(contactController.getNewForm(), is(notNullValue()));
	}

	@Test
	public void whenThereAreFormErrorsReturnToInput() throws Exception {
		ContactForm message = new ContactForm();
		when(bindingResult.hasErrors()).thenReturn(true);
		
		String result = contactController.submit(message, bindingResult, redirectAttributes);
		
		assertThat(result, is(equalTo("contact")));
	}

	@Test
	public void whenThereAreNoErrorsProcessMessage() throws Exception {
		ContactForm message = new ContactForm();
		
		String result = contactController.submit(message, bindingResult, redirectAttributes);
		
		assertThat(result, is(equalTo("redirect:/contact")));
		verify(contactService).process(message);
	}

	@Test
	public void whenThereAreNoErrorsAddSuccessMessage() throws Exception {
		ContactForm message = new ContactForm();
		
		contactController.submit(message, bindingResult, redirectAttributes);
		
		verify(redirectAttributes).addFlashAttribute("success", "Message has been sent!");
	}
}
