package com.zombietank.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import com.google.common.base.Preconditions;
import com.google.common.io.CharStreams;
import com.google.common.io.InputSupplier;

public class InputSuppliers {
	
	public static InputSupplier<InputStreamReader> newReaderSupplier(final InputStream inputStream, Charset charset) {
		return CharStreams.newReaderSupplier(forInputStream(inputStream), charset);
	}	
	
	public static InputSupplier<InputStream> forInputStream(final InputStream inputStream) {
		Preconditions.checkNotNull(inputStream, "Cannot create input supplier for null InputStream.");
		return new InputSupplier<InputStream>() {
			@Override
			public InputStream getInput() throws IOException {
				return inputStream;
			}
		};
	}
}
