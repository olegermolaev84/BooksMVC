package com.gmail.olegermolaev84.books.models;

import java.util.List;

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
	
	public BooksFilterForm() {
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("BooksFilterForm( ");
		sb.append("selectedAuthorId=" + selectedAuthorId + ", ");
		sb.append("selectedGenreId=" + selectedGenreId + ", ");
		sb.append("nameLike=" + nameLike + ", ");
		return sb.toString();
	}
}
