package com.zombietank.io;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.google.common.io.InputSupplier;

@RunWith(MockitoJUnitRunner.class)
public class InputSuppliersTest {
	@Mock
	private InputStream inputStream;

	@Test
	public void providesInputSupplierForInputStream() throws Exception {
		InputSupplier<InputStream> inputSupplier = InputSuppliers.forInputStream(inputStream);
		
		assertThat(inputSupplier.getInput(), is(sameInstance(inputStream)));
	}
	
	
	@Test
	public void newReaderSupplierForInputStream() throws Exception {
		String inputString = UUID.randomUUID().toString();
		InputStream stringInputStream = new ByteArrayInputStream(inputString.getBytes(Charsets.UTF_8));
		
		InputSupplier<InputStreamReader> supplier = InputSuppliers.newReaderSupplier(stringInputStream, Charsets.UTF_8);
		
		assertEquals(inputString, CharStreams.toString(supplier));
	}
	
	@Test
	public void readerSupplierHasCorrectEncoding() throws Exception {
		InputSupplier<InputStreamReader> supplier = InputSuppliers.newReaderSupplier(inputStream, Charsets.ISO_8859_1);
		
		assertTrue(Charsets.ISO_8859_1.aliases().contains(supplier.getInput().getEncoding()));
	}
	
	@Test(expected=NullPointerException.class)
	public void cannotGetSupplierForNullInputStream() throws Exception {
		InputSuppliers.forInputStream(null);
	}
}
