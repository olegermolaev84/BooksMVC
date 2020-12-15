package com.gmail.olegermolaev84.books.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gmail.olegermolaev84.books.models.Author;

@Transactional
public interface AuthorRepository extends CrudRepository<Author, Long> {
	@Query("Select a from Author a order by a.name asc")
	public List<Author> findAllOrderedByName();
	
	@Query("Select max(a.id) from Author a")
	public long maxId();
	
	public void deleteByIdIn(List<Long> ids);
}
