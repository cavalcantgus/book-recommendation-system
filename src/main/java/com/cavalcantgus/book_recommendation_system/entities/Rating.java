package com.cavalcantgus.book_recommendation_system.entities;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity // Mapeada como entidade JPA
@Table(name = "rating")
public class Rating {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) // Geração automática de ID
	private Long id;
	
	@ManyToOne // Relacionamento bidirecional com User
	@JoinColumn(name = "user_id") // Um objeto User está associado a vários objetos Rating
	private User user;
	
	@ManyToOne // Relacionamento bidirecional com Book
	@JoinColumn(name = "book_id") // Um objeto Book está associado a vários objetos Rating
	private Book book;
	private Integer rating;
	private String review;
	private Timestamp created_at;
	
	// Construtor padrão
	public Rating() {}
	
	// Construtor parametrizado
	public Rating(User user, Book book, Integer rating, String review) {
		this.user = user;
		this.book = book;
		this.rating = rating;
		this.review = review;
		this.created_at = new Timestamp(System.currentTimeMillis());
	}

	// Getters & Setters
	public Long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public Book getBook() {
		return book;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	
}
