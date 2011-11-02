package com.zombietank.email.support;

import javax.mail.internet.InternetAddress;

public final class InternetAddressBuilder {

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
}
