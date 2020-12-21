package com.gmail.olegermolaev84.books.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gmail.olegermolaev84.books.models.Book;
import com.gmail.olegermolaev84.books.models.Books;
import com.gmail.olegermolaev84.books.models.BooksFilterForm;
import com.gmail.olegermolaev84.books.repositories.AuthorRepository;
import com.gmail.olegermolaev84.books.repositories.BookRepository;
import com.gmail.olegermolaev84.books.repositories.GenreRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/books")
@AllArgsConstructor
@Slf4j
public class BooksController {
	
	private AuthorRepository authorRepo;
	private GenreRepository genreRepo;
	private BookRepository bookRepo;

	@GetMapping
	public String showBooks(Model model) {
		
		BooksFilterForm booksFilterForm = new BooksFilterForm();
		booksFilterForm.setAuthors(authorRepo.findAllOrderedByName());
		booksFilterForm.setGenres(genreRepo.findAllOrderedByName());
		
		model.addAttribute("booksFilterForm", booksFilterForm);
		model.addAttribute("books", new Books());
		model.addAttribute("showBooks", false);
		
		return "books";
	}
	
	@PostMapping
	public String handleShowAndUpdateRequest(@Valid BooksFilterForm booksFilterForm, 
			BindingResult bindingResult, Model model) {
		
		booksFilterForm.setAuthors(authorRepo.findAllOrderedByName());
		booksFilterForm.setGenres(genreRepo.findAllOrderedByName());
		
		if(bindingResult.hasErrors()) {
			return "books";
		}
		model.addAttribute("books", new Books(getBooksByFilter(booksFilterForm)));
		model.addAttribute("showBooks", true);
		return "books";
	}
	
	private List<Book> getBooksByFilter(BooksFilterForm booksFilterForm) {
		log.info(booksFilterForm.toString());
		
		// No filters
		if(booksFilterForm.getNameLike().isEmpty() &&
		   booksFilterForm.getSelectedAuthorId() == -1 &&
		   booksFilterForm.getSelectedGenreId() == -1) {
			return bookRepo.findAllOderedByName();
		}
		
		// Filter by book name
		else if(!booksFilterForm.getNameLike().isEmpty() &&
				   booksFilterForm.getSelectedAuthorId() == -1 &&
				   booksFilterForm.getSelectedGenreId() == -1) {
			return bookRepo.findByNameLikeOrderByNameAsc("%"+booksFilterForm.getNameLike()+"%");
		}
		
		// Filter by author
		else if(booksFilterForm.getNameLike().isEmpty() &&
				   booksFilterForm.getSelectedAuthorId() != -1 &&
				   booksFilterForm.getSelectedGenreId() == -1) {
			return bookRepo.findByAuthorIdOrderByNameAsc(booksFilterForm.getSelectedAuthorId());
		}
		
		// Filter by genre
		else if(booksFilterForm.getNameLike().isEmpty() &&
				   booksFilterForm.getSelectedAuthorId() == -1 &&
				   booksFilterForm.getSelectedGenreId() != -1) {
			return bookRepo.findByGenreIdOrderByNameAsc(booksFilterForm.getSelectedGenreId());
		}
		
		// Filter by book name and author
		else if(!booksFilterForm.getNameLike().isEmpty() &&
				   booksFilterForm.getSelectedAuthorId() != -1 &&
				   booksFilterForm.getSelectedGenreId() == -1) {
			return bookRepo.findByAuthorIdAndNameLikeOrderByNameAsc(
					booksFilterForm.getSelectedAuthorId(), 
					"%"+booksFilterForm.getNameLike()+"%");
		}
		
		// Filter by book name and genre
		else if(!booksFilterForm.getNameLike().isEmpty() &&
				   booksFilterForm.getSelectedAuthorId() == -1 &&
				   booksFilterForm.getSelectedGenreId() != -1) {
			return bookRepo.findByGenreIdAndNameLikeOrderByNameAsc(
					booksFilterForm.getSelectedGenreId(), 
					"%"+booksFilterForm.getNameLike()+"%");
		}
		
		// Filter by author and genre
		else if(booksFilterForm.getNameLike().isEmpty() &&
				   booksFilterForm.getSelectedAuthorId() != -1 &&
				   booksFilterForm.getSelectedGenreId() != -1) {
			return bookRepo.findByAuthorIdAndGenreIdOrderByNameAsc(
					booksFilterForm.getSelectedAuthorId(), 
					booksFilterForm.getSelectedGenreId());
		}
		
		// Filter by author, genre and title
		else if(!booksFilterForm.getNameLike().isEmpty() &&
				   booksFilterForm.getSelectedAuthorId() != -1 &&
				   booksFilterForm.getSelectedGenreId() != -1) {
			return bookRepo.findByAuthorIdAndGenreIdAndNameLikeOrderByNameAsc(
					booksFilterForm.getSelectedAuthorId(), 
					booksFilterForm.getSelectedGenreId(), 
					"%"+booksFilterForm.getNameLike()+"%");
		}
						
		
		return new ArrayList<Book>();
	}
}
