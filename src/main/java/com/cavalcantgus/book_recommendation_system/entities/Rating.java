package com.cavalcantgus.book_recommendation_system.entities;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "rating")
@Getter
@Setter
@NoArgsConstructor
public class Rating {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(AccessLevel.NONE)
	private Long id;

	@ManyToOne // Relacionamento bidirecional com User
	@JoinColumn(name = "user_id") // Um objeto User está associado a vários objetos Rating
	private User user;

	@ManyToOne // Relacionamento bidirecional com Book
	@JoinColumn(name = "book_id") // Um objeto Book está associado a vários objetos Rating
	private Book book;
	private Integer rating;
	private String review;
	private Timestamp createdAt;

	// Construtor parametrizado
	public Rating(User user, Book book, Integer rating, String review) {
		this.user = user;
		this.book = book;
		this.rating = rating;
		this.review = review;
	}

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Timestamp(System.currentTimeMillis());
	}

}
