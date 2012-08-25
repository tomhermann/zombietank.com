package com.zombietank.db;

import java.util.Collection;

public interface CrudRepository<T extends Persistable> {
	void save(T item);
	void delete(T item);
	void deleteById(long id);
	T get(long id);
	Collection<T> getAll();
}
