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
@Table(name = "recommendation")
@Getter
@Setter
@NoArgsConstructor
public class Recommendation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(AccessLevel.NONE)
	private Long id;

	@ManyToOne // Relacionamento bidirecional com User
	@JoinColumn(name = "user_id") // Um objeto User está associado a vários objetos Recommendation
	private User user;

	@ManyToOne // Relacionamento bidirecional com Book
	@JoinColumn(name = "book_id") // Um objeto Book está associado a vários objetos Recommendation
	private Book book;
	private Double score;
	private Timestamp createdAt;

	// Construtor parametrizado
	public Recommendation(User user, Book book, Double score) {
		this.user = user;
		this.book = book;
		this.score = score;

	}

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Timestamp(System.currentTimeMillis());
	}

}
