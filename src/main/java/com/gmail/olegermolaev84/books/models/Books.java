package com.gmail.olegermolaev84.books.models;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Books {

	private List<Book> books;
	
	public Books() {
		books = new ArrayList<Book>(); 
	}
	
	public Books(List<Book> books) {
		books.forEach((book)->book.convertAuthorsToString());
		this.books = books;
	}
	
	public void addBook(Book book) {
		book.convertAuthorsToString();
		books.add(book);
	}

}
