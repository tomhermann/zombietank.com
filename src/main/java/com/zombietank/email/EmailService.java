package com.zombietank.email;

import java.io.Serializable;
import java.util.Map;

public interface EmailService {
	void send(Email email);
	void send(Email email, String templateLocation);
	<T extends Serializable> void send(Email email, String templateLocation, String modelKey, T modelValue);
	void send(Email email, String templateLocation, Map<String, ? extends Serializable> model);
}