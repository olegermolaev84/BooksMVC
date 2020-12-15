package com.gmail.olegermolaev84.books.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gmail.olegermolaev84.books.models.BooksFilterForm;
import com.gmail.olegermolaev84.books.repositories.AuthorRepository;
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

	@GetMapping
	public String showBooks(Model model) {
		BooksFilterForm booksFilterForm = new BooksFilterForm();
		booksFilterForm.setAuthors(authorRepo.findAllOrderedByName());
		booksFilterForm.setGenres(genreRepo.findAllOrderedByName());
		model.addAttribute("booksFilterForm", booksFilterForm);
		
		return "books";
	}
	
	@PostMapping
	public String handleShowAndUpdateRequest(@Valid BooksFilterForm booksFilterForm, 
			BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			booksFilterForm.setAuthors(authorRepo.findAllOrderedByName());
			booksFilterForm.setGenres(genreRepo.findAllOrderedByName());
			return "books";
		}
		return "redirect:/books";
	}
}
