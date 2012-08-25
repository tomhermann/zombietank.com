package com.zombietank.blog;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.zombietank.db.CrudJdbcRepository;

@Repository
public class BlogJdbcRepository extends CrudJdbcRepository<Blog> implements BlogRepository {

	@Inject
	public BlogJdbcRepository(DataSource dataSource) {
		super(Blog.class, dataSource, "blog_entries");
	}
}
