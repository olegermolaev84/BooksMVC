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

import com.gmail.olegermolaev84.books.models.Genre;
import com.gmail.olegermolaev84.books.models.Genres;
import com.gmail.olegermolaev84.books.repositories.GenreRepository;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/genres")
@Slf4j
public class GenresController {
	
	private GenreRepository repo;
	
	public GenresController(GenreRepository repo) {
		this.repo = repo;
	}

	@GetMapping
	public String showGenres(Model model, @RequestParam(required = false, defaultValue="") String customerMistake) {
		model.addAttribute("genres", new Genres(repo.findAllOrderedByName()));
		model.addAttribute("customerMistake", customerMistake);
		return "genres";
	}
	
	@PostMapping
	public String handleUpdateRequest(@ModelAttribute Genres genres, @RequestParam String selectedAction, RedirectAttributes ra) {
		StringBuilder sb = new StringBuilder();
		sb.append(selectedAction + " request for genre IDs': ");
		List<Long> ids = genres.getGenres().stream()
				                  .filter((genre)->genre.isSelected())
				                  .map((genre)->genre.getId())
				                  .collect(Collectors.toList());
		ids.forEach((id)->sb.append("" + id + ", "));
		log.info(sb.toString());
		
		if(selectedAction.equals("delete")) {
			return deleteGenres(ids, ra);
		}
		else if(selectedAction.equals("update")) {
			return updateGenre(ids, ra);
		}
		else {
			throw new IllegalArgumentException("Unsupported value of selectedAction param: " + selectedAction);
		}
	}
	
	private String deleteGenres(List<Long> ids, RedirectAttributes ra) {
		//
		// ПРОВЕРИТЬ НАЛИЧИЕ КНИГ ПЕРЕД УДАЛЕНИЕМ
		//
		repo.deleteByIdIn(ids);
		return "redirect:/genres";
	}
	
	private String updateGenre(List<Long> ids, RedirectAttributes ra) {
		// Only one id is to be in the list
		if(ids.size() != 1) {
			ra.addAttribute("customerMistake", "Для редактирования должна быть выбрана одна позиция");
			return "redirect:/genres";
		}
		else {
			ra.addAttribute("genre_id", ids.get(0));
			return "redirect:/genres/edit";
		}
	}
	
	@GetMapping("/edit")
	public String showEditForm(Model model, @RequestParam(required=false) Long genre_id) {
		log.info("Open edit form for genre ID:  " + genre_id);
		
		if(genre_id == null) { // create new genre
			Genre genre = new Genre();
			genre.setExistent(false);
			model.addAttribute("genre", genre);
		}
		else { // update existent genre
			Genre genre = repo.findById(genre_id).get();
			genre.setExistent(true);
			model.addAttribute("genre", genre);
		}
		return "genres/edit";
	}
	
	@PostMapping("/edit")
	public String saveGenre(@Valid Genre genre, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "genres/edit";
		}
		
		if(!genre.isExistent()) {
			genre.setId(repo.maxId()+1);
			log.info("Creating new genre:  " + genre.toString());
			repo.save(genre);
		}
		else {
			Genre genreFromDB = repo.findById(genre.getId()).get();
			genreFromDB.copyOf(genre);
			log.info("Updating genre:  " + genre.toString());
			repo.save(genreFromDB);		
		}
		return "redirect:/genres";
	}
}
