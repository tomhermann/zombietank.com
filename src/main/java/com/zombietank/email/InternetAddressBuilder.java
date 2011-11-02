package com.zombietank.email;

import javax.mail.internet.InternetAddress;

public class InternetAddressBuilder {

	public static InternetAddress newAddress(String address) {
		try {
			return new InternetAddress(address);
		} catch (Exception e) {
			throw new AddressException(e);
		}
	}

	public static InternetAddress newAddress(String address, String personal) {
		try {
			return new InternetAddress(address, personal);
		} catch (Exception e) {
			throw new AddressException(e);
		}
	}
	
	private InternetAddressBuilder() {
	}
	
	private static final class AddressException extends RuntimeException {
		private static final long serialVersionUID = 1L;
		public AddressException(Throwable cause) { super(cause); }
	}
}
