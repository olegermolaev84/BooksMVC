package com.gmail.olegermolaev84.books.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gmail.olegermolaev84.books.models.Author;
import com.gmail.olegermolaev84.books.models.Authors;
import com.gmail.olegermolaev84.books.repositories.AuthorRepository;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/authors")
@Slf4j
public class AuthorsController {
	private AuthorRepository repo;
	
	public AuthorsController(AuthorRepository repo) {
		this.repo = repo;
	}

	@GetMapping
	public String showAuthors(Model model, @RequestParam(required = false, defaultValue="") String customerMistake) {
		model.addAttribute("authors", new Authors(repo.findAllOrderedByName()));
		model.addAttribute("customerMistake", customerMistake);
		return "authors";
	}
	
	@PostMapping
	public String handleUpdateRequest(@ModelAttribute Authors authors, @RequestParam String selectedAction, RedirectAttributes ra) {
		StringBuilder sb = new StringBuilder();
		sb.append(selectedAction + " request for author IDs': ");
		List<Long> ids = 
				authors.getAuthors().stream()
				                    .filter((genre)->genre.isSelected())
				                    .map((genre)->genre.getId())
				                    .collect(Collectors.toList());
		ids.forEach((id)->sb.append("" + id + ", "));
		log.info(sb.toString());
		
		if(selectedAction.equals("delete")) {
			return deleteAuthors(ids, ra);
		}
		else if(selectedAction.equals("update")) {
			return updateAuthor(ids, ra);
		}
		else {
			throw new IllegalArgumentException("Unsupported value of selectedAction param: " + selectedAction);
		}
	}
	
	private String deleteAuthors(List<Long> ids, RedirectAttributes ra) {
		//
		// ПРОВЕРИТЬ НАЛИЧИЕ КНИГ ПЕРЕД УДАЛЕНИЕМ
		//
		repo.deleteByIdIn(ids);
		return "redirect:/authors";
	}
	
	private String updateAuthor(List<Long> ids, RedirectAttributes ra) {
		// Only one id is to be in the list
		if(ids.size() != 1) {
			ra.addAttribute("customerMistake", "Для редактирования должн быть выбран один автор");
			return "redirect:/authors";
		}
		else {
			ra.addAttribute("author_id", ids.get(0));
			return "redirect:/authors/edit";
		}
	}
	
	
	@GetMapping("/edit")
	public String showAddAuthorForm(Model model, @RequestParam(required=false) Long author_id) {
		log.info("Open edit form for author ID:  " + author_id);
		
		if(author_id == null) { // create new author
			Author author = new Author();
			author.setExistent(false);
			model.addAttribute("author", author);
		}
		else { // update existent genre
			Author author = repo.findById(author_id).get();
			author.setExistent(true);
			model.addAttribute("author", author);
		}
		
		return "authors/edit";
	}
	
	@PostMapping("/edit")
	public String addAuthor(@Valid Author author, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "authors/edit";
		}
		
		if(!author.isExistent()) {
			author.setId(repo.maxId()+1);
			log.info("Creating new author: " + author.toString());
			repo.save(author);
		}
		else {
			Author authorFromDB = repo.findById(author.getId()).get();
			authorFromDB.copyOf(author);
			log.info("Updating author: " + author.toString());
			repo.save(authorFromDB);
		}
		return "redirect:/authors";
	}
}
