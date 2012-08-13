package com.zombietank.contact;

import java.util.Collection;

public interface ContactMessageRepository {
	void save(ContactMessage message);
	void delete(ContactMessage message);
	void deleteById(Number id);
	ContactMessage getMessage(Number id);
	Collection<ContactMessage> getAllMessages();
}
