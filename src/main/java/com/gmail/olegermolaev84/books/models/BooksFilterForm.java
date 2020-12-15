package com.gmail.olegermolaev84.books.models;

import java.util.List;

import javax.validation.constraints.Digits;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BooksFilterForm {
	private Long selectedAuthorId;
	private List<Author> authors;
	private Long selectedGenreId;
	private List<Genre> genres;
	
	// substring which is to be in the books' names
	private String nameLike;
	 
	@Digits(fraction = 0, integer = 4, message="Неправильный формат года в поле 'от'")
	private Integer minYearBookPublishing;
	@Digits(fraction = 0, integer = 4, message="Неправильный формат года в поле 'до'")
	private Integer maxYearBookPublishing;
	
	public BooksFilterForm() {
	}
}
