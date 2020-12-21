package com.gmail.olegermolaev84.books.security.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "authority")
public class Authority {
	public final static String ADMIN_ROLE = "ADMIN_ROLE";
	public final static String REGULAR_ROLE = "REGULAR_ROLE";
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="authority", length = 15)
	private String authority;
}
