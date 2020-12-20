package com.gmail.olegermolaev84.books.security.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gmail.olegermolaev84.books.security.models.Authority;

@Repository
public interface AuthorityRepository extends CrudRepository<Authority, Long> {
	public Authority findByAuthority(String authority);
}
