package com.gmail.olegermolaev84.books.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
@Table(name="author")
public class Author {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min=5, max=256, message="Имя автора должно быть от 5 до 256 символов")
	@Column(name="name", length = 256)
	@NotNull
	private String name;
	
	@Temporal(value=TemporalType.DATE)
	@Column(name="date_of_born")
	@NotNull(message = "Дата рождения должна быть указана")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfBorn;
	
	@Temporal(value=TemporalType.DATE)
	@Column(name="date_of_died")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfDied;
	
	@ManyToMany(mappedBy = "authors")
	private Set<Book> books;
	
	@Transient
	private boolean selected;	
	
	@Transient
	private boolean existent;
	
	public void copyOf(Author author) {
		this.books = author.books;
		this.dateOfBorn = author.dateOfBorn;
		this.dateOfDied = author.dateOfDied;
		this.id = author.id;
		this.name = author.name;
		this.selected = author.selected;
		this.existent = author.existent;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Author( ");
		sb.append("id=" + id + ", ");
		sb.append("name=" + name + ", ");
		sb.append("dateOfBorn=" + dateOfBorn + ", ");
		sb.append("dateOfDied=" + dateOfDied + ", ");
		sb.append("selected=" + selected + ", ");
		sb.append("existent=" + existent + ")");
		return sb.toString();
	}
}
