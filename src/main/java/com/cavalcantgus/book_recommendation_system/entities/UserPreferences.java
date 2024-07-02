package com.cavalcantgus.book_recommendation_system.entities;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "userpreferences")
@Getter
@Setter
@NoArgsConstructor
public class UserPreferences {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(AccessLevel.NONE)
	private Long id;

	@OneToOne // Relacionamento bidirecional com User
	@JoinColumn(name = "user_id", unique = true) // Campo único e relacionamento 1 para 1
	private User user;

	@ManyToOne // Relacionamento bidirecional com Genre
	@JoinColumn(name = "genre_id") // Um objeto Genre está associado a vários objetos UserPreferences
	private Genre genre;
	private Double preferenceScore;
	private Timestamp createdAt;

	// Construtor parametrizado
	public UserPreferences(User user, Genre genre, Double preferenceScore) {
		this.user = user;
		this.genre = genre;
		this.preferenceScore = preferenceScore;
	}

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Timestamp(System.currentTimeMillis());
	}

}
