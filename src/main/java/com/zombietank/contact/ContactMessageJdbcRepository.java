package com.zombietank.contact;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.zombietank.db.CrudJdbcRepository;

@Repository
public class ContactMessageJdbcRepository extends CrudJdbcRepository<ContactMessage> implements ContactMessageRepository {

	@Inject
	public ContactMessageJdbcRepository(DataSource dataSource) {
		super(ContactMessage.class, dataSource, "contact_messages");
	}
}
