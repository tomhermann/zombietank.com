package com.zombietank.io;

public class ExceptionHandlers {

	public static <T> ExceptionHandler<T> nullExceptionHandler() {
		return new NullExceptionHandler<T>(false);
	}

	public static <T> ExceptionHandler<T> stackTracePrintingExceptionHandler() {
		return new NullExceptionHandler<T>(true);
	}

	private static class NullExceptionHandler<T> implements ExceptionHandler<T> {
		private final boolean printStackTrace;

		public NullExceptionHandler(boolean printStackTrace) {
			this.printStackTrace = printStackTrace;
		}

		@Override
		public T handle(Exception exception) {
			if(printStackTrace) {
				exception.printStackTrace();
			}
			return null;
		}
	}
}
