package com.gmail.olegermolaev84.books.security.models;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table (name="users")
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
	
	private static final long serialVersionUID = -7143742316275509165L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min=3, max=25, message="Имя пользователя должно быть от 3 до 25 символов")
	@Column(name="username", length = 25)
	private String username;
	
	@Size(min=5, message="Пароль должен быть не менее 5 символов")
	@Column(name="password")
	private String password;
	
	@Transient
	private String repeatedPassword;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "authority_id")
	private Authority authority;
	
	@Temporal(value=TemporalType.DATE)
	@Column(name="exparation_date")
	private Date exparationDate;
	
	@Column(name = "locked")
	private boolean locked;
	
	@Column(name = "crendentials_expired")
	private boolean crendentials_expired;
	
	@Column(name = "enabled")
	private boolean enabled;
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority(authority.getAuthority()));
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return exparationDate.compareTo(new Date()) == 1;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !crendentials_expired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}
	
	public void encodePassword(PasswordEncoder passwordEncoder) {
		password = passwordEncoder.encode(password);
	}
}
