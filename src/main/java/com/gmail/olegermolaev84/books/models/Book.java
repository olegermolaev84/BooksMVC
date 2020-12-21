package com.gmail.olegermolaev84.books.models;

import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name="book")
public class Book {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min=3, max=256, message="Название книги должно быть от 3 до 256 символов")
	@Column(name="name", length = 256)
	@NotNull
	private String name;
	
	@Column(name="year_of_publishing")
	@NotNull(message = "Год публикации должн быть указан")
	private int yearOfPublishing;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "genre_id")
	@NotNull(message="Жанр должен быть указан")
	private Genre genre;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
			  name = "book_author", 
			  joinColumns = @JoinColumn(name = "book_id"), 
			  inverseJoinColumns = @JoinColumn(name = "author_id"))
	private List<Author> authors;
	
	@Column(name="url", length = 256)
	private String url;
	
	@Transient
	private String authorsAsString;
	
	@Transient
	private boolean selected;
	
	@Transient
	private boolean existent;
	
	public void copyOf(Book book) {
		this.authors = book.authors;
		this.yearOfPublishing = book.yearOfPublishing;
		this.genre = book.genre;
		this.id = book.id;
		this.name = book.name;
		this.selected = book.selected;
		this.url = book.url;
		this.existent = book.existent;
	}
	
	public void convertAuthorsToString() {
		StringBuilder sb = new StringBuilder();
		
		Iterator<Author> iter = authors.iterator();
		while(iter.hasNext()) {
			sb.append(iter.next().getName());
			if(iter.hasNext()) {
				sb.append(", ");
			}
		}
		
		authorsAsString = sb.toString();
	}
}
