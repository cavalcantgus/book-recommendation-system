package com.cavalcantgus.book_recommendation_system.entities;

import java.sql.Timestamp;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "recommendation")
public class Recommendation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "book_id")
	private Book book;
	private Double score;
	private Timestamp created_at;
	
	public Recommendation() {}
	
	public Recommendation(User user, Book book, Double score) {
		this.user = user;
		this.book = book;
		this.score = score;
		this.created_at = new Timestamp(System.currentTimeMillis());
	}

	public Long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public Book getBook() {
		return book;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Timestamp getCreatedAt() {
		return created_at;
	}
	
}
