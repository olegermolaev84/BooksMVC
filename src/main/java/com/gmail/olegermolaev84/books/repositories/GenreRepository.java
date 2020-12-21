package com.gmail.olegermolaev84.books.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gmail.olegermolaev84.books.models.Genre;

@Transactional
@Repository
public interface GenreRepository extends CrudRepository<Genre, Long> {
	@Query("Select g from Genre g order by g.name asc")
	public List<Genre> findAllOrderedByName();
	
	public void deleteByIdIn(List<Long> ids);
}
