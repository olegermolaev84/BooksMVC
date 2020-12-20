package com.gmail.olegermolaev84.books.security;

import java.util.Calendar;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gmail.olegermolaev84.books.security.models.Authority;
import com.gmail.olegermolaev84.books.security.models.User;
import com.gmail.olegermolaev84.books.security.repositories.AuthorityRepository;
import com.gmail.olegermolaev84.books.security.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/register")
@Slf4j
public class RegistrationController {

	private UserRepository userRepo;
	private PasswordEncoder passwordEncoder;
	private AuthorityRepository authorityRepo;
	
	public RegistrationController(UserRepository userRepo,
			PasswordEncoder passwordEncoder,
			AuthorityRepository authorityRepo) {
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
		this.authorityRepo = authorityRepo;
	}
	
	@GetMapping("/create_admin")
	public String addAdmin() {
		Optional<User> res = userRepo.findById(0L);
		if(res.isEmpty()) {
			 Calendar calendar = Calendar.getInstance();
			 calendar.set(2100, 1, 1);
			    
			User admin = new User(null, 
					"admin", 
					passwordEncoder.encode("12345"), 
					"",
					authorityRepo.findByAuthority(Authority.ADMIN_ROLE), 
					calendar.getTime(), 
					false, 
					false, 
					true);
			
			userRepo.save(admin);
		}
		
		return "redirect:/";
	}
	
	@GetMapping
	public String registerForm(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("passwordsMatch", true);
		return "registration";
	}
	
	@PostMapping
	public String createUser(@Valid User user, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors() || !user.getPassword().equals(user.getRepeatedPassword())) {
			model.addAttribute("passwordsMatch", user.getPassword().equals(user.getRepeatedPassword()));
			return "registration";
		}
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(2100, 1, 1);
		
		user.setAuthority(authorityRepo.findByAuthority(Authority.REGULAR_ROLE));
		user.setCrendentials_expired(false);
		user.setEnabled(true);
		user.setExparationDate(calendar.getTime());
		user.setLocked(false);
		user.encodePassword(passwordEncoder);
		
		log.info("User to be created: " + user.toString());
		userRepo.save(user);
		
		return "redirect:/";
	}
}
