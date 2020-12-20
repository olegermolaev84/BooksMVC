package com.gmail.olegermolaev84.books.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gmail.olegermolaev84.books.security.models.User;
import com.gmail.olegermolaev84.books.security.repositories.UserRepository;

@Service
public class UserDetailsServiceJPA implements UserDetailsService {

	private UserRepository repo;
	
	@Autowired
	public UserDetailsServiceJPA(UserRepository repo) {
		this.repo = repo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repo.findByUsername(username);
		if(user != null) {
			return user;
		}
		throw new UsernameNotFoundException("User '" + username + "' not found");
	}

}
