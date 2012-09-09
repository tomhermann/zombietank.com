package com.zombietank.io;

public interface ExceptionHandler<T> {
	T handle(Exception exception);
}
