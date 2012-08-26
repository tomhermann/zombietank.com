package com.zombietank.blog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long>, JpaSpecificationExecutor<Comment> {
	Page<Comment> findByBlog(Blog blog, Pageable pageable);
}
