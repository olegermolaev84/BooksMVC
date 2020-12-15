package com.gmail.olegermolaev84.books.models;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Genres {
	private List<Genre> genres;
	
	public Genres() {
		genres = new ArrayList<Genre>();
	}
	
	public Genres(List<Genre> genres) {
		this.genres = genres;
	}
	
	public void addGenre(Genre genre) {
		genres.add(genre);
	}
}
