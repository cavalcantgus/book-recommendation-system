package com.cavalcantgus.book_recommendation_system.entities;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "author")
public class Author {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	private String description;
	
	@OneToMany(mappedBy = "author")
	private Set<Book> books;
	
	private Date dateOfBirth;
	private Timestamp created_at;
	private Timestamp updated_at;
	
	public Author() {}
	
	public Author(String name, String description, Date dateOfBirth) {
		this.name = name;
		this.description = description;
		this.dateOfBirth = dateOfBirth;
		this.created_at = new Timestamp(System.currentTimeMillis());
		this.updated_at = created_at;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
		setUpdated_at(new Timestamp(System.currentTimeMillis()));
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
		setUpdated_at(new Timestamp(System.currentTimeMillis()));
	}
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
		setUpdated_at(new Timestamp(System.currentTimeMillis()));
	}
	
	public Timestamp getCreated_at() {
		return created_at;
	}
	
	public Timestamp getUpdated_at() {
		return updated_at;
	}
	
	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}
	
	public Set<Book> getBooks(){
		return books;
	}
}
