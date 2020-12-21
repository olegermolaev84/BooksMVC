package com.gmail.olegermolaev84.books.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gmail.olegermolaev84.books.models.Author;

@Transactional
@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
	@Query("Select a from Author a order by a.name asc")
	public List<Author> findAllOrderedByName();
	
	public void deleteByIdIn(List<Long> ids);
}
