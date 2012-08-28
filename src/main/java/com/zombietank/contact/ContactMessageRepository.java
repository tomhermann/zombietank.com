package com.zombietank.contact;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ContactMessageRepository extends CrudRepository<ContactMessage, Long> {
	public List<ContactMessage> findAll();
}
