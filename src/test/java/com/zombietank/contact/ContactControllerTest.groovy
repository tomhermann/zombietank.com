package com.zombietank.contact;

import static org.hamcrest.Matchers.*
import static org.junit.Assert.*
import static org.mockito.BDDMockito.*
import static org.mockito.Mockito.*

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner
import org.springframework.validation.BindingResult
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@RunWith(MockitoJUnitRunner.class)
class ContactControllerTest {
	@InjectMocks ContactController contactController
	@Mock ContactService contactService
	@Mock RedirectAttributes redirectAttributes
	@Mock BindingResult bindingResult
	
	@Test
	void getNewFormReturnsNewFormInstance() {
		assertThat contactController.getContactMessage(), notNullValue()
	}

	@Test
	void whenThereAreFormErrorsReturnToInput() throws Exception {
		def message = new ContactMessage()
		given(bindingResult.hasErrors()).willReturn(true)
		
		String result = contactController.submit(message, bindingResult, redirectAttributes)
		
		assertThat result, equalTo("contact")
	}

	@Test
	void whenThereAreNoErrorsProcessMessage() throws Exception {
		def message = new ContactMessage()
		
		String result = contactController.submit(message, bindingResult, redirectAttributes);
		
		verify(contactService).process(message)
	}
	
	@Test
	void whenThereAreNoErrorsRedirectToContactPage() throws Exception {
		def message = new ContactMessage()
		
		String result = contactController.submit(message, bindingResult, redirectAttributes);
		
		assertThat result, equalTo("redirect:/contact")
	}

	@Test
	void whenThereAreNoErrorsAddSuccessMessage() {
		def message = new ContactMessage()
		
		contactController.submit(message, bindingResult, redirectAttributes)
		
		verify(redirectAttributes).addFlashAttribute("success", "Message has been sent!")
	}
}
