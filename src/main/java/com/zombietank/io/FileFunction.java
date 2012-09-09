package com.zombietank.io;

import java.io.File;
import java.io.IOException;

import com.google.common.base.Function;

public abstract class FileFunction<T> implements Function<File, T> {
	private ExceptionHandler<T> exceptionHandler;

	public FileFunction() {
		this(ExceptionHandlers.<T>nullExceptionHandler());
	}
	
	public FileFunction(ExceptionHandler<T> exceptionHandler) {
		this.exceptionHandler = exceptionHandler;
	}

	public abstract T handle(File file) throws IOException;
	
	@Override
	public T apply(File file) {
		try {
			return handle(file);
		} catch (IOException e) {
			return exceptionHandler.handle(e);
		}
	}

	protected void setExceptionHandler(ExceptionHandler<T> exceptionHandler) {
		this.exceptionHandler = exceptionHandler;
	}
}
