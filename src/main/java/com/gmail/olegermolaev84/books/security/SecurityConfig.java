package com.gmail.olegermolaev84.books.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private UserDetailsService userDetailsService;
	
//	private static final String[] EDITABLE_PAGES = 
//		{
//		 "/authors/edit",
//		 "/genres/edit"
//		} ;
	
	private static final String[] NO_AUTHORITY_PAGES = 
		{
		"/",
		"/images/**",
		"/style.css",
		"/home",
		"/register",
		"/register/create_admin",
		"/login"
		};
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder(BCryptVersion.$2A);
	}
	
	@Autowired
	public SecurityConfig(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth
			.userDetailsService(userDetailsService)
			.passwordEncoder(encoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			.antMatchers(NO_AUTHORITY_PAGES)
				.permitAll()
//			.antMatchers(EDITABLE_PAGES)
//				.hasRole("ADMIN_ROLE")
			.antMatchers("/**")
				.authenticated()
		.and()
			.formLogin()
				.loginPage("/login")
		.and()
			.logout()
				.logoutSuccessUrl("/");
	}
}
