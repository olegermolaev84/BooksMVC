package com.gmail.olegermolaev84.books.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gmail.olegermolaev84.books.models.Genre;

@Transactional
public interface GenreRepository extends CrudRepository<Genre, Long> {
	@Query("Select g from Genre g order by g.name asc")
	public List<Genre> findAllOrderedByName();
	
	@Query("Select max(g.id) from Genre g")
	public long maxId();
	
	public void deleteByIdIn(List<Long> ids);
}
