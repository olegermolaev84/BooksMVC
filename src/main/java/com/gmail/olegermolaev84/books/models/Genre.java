package com.gmail.olegermolaev84.books.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
@Table(name = "genre")
public class Genre {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", length = 256)
	@NotNull
	@Size(min=5, max=256, message="Название жанра должно состоять не менее чем из 5 символов и не более чем из 256 символов!")
	private String name;
	
	@Transient
	private boolean selected;
	
	@Transient
	private boolean existent;
	
	public void copyOf(Genre genre) {
		this.id = genre.id;
		this.existent = genre.existent;
		this.name = genre.name;
		this.selected = genre.selected;
	}
}
