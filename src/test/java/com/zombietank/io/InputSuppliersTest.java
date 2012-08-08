package com.zombietank.io;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.io.InputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.io.InputSupplier;

@RunWith(MockitoJUnitRunner.class)
public class InputSuppliersTest {
	@Mock
	private InputStream inputStream;

	@Test
	public void providesGetSupplierForInputStream() throws Exception {
		InputSupplier<InputStream> inputSupplier = InputSuppliers.forInputStream(inputStream);
		
		assertThat(inputSupplier.getInput(), is(sameInstance(inputStream)));
	}
	
	@Test(expected=NullPointerException.class)
	public void cannotGetSupplierForNullInputStream() throws Exception {
		InputSuppliers.forInputStream(null);
	}
}
