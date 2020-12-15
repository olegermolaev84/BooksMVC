package com.gmail.olegermolaev84.books.models;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Authors {
	private List<Author> authors;
	
	public Authors() {
		authors = new ArrayList<Author>();
	}
	
	public Authors(List<Author> authors) {
		this.authors = authors;
	}
	
	public void addAuthor(Author author) {
		authors.add(author);
	}
}
