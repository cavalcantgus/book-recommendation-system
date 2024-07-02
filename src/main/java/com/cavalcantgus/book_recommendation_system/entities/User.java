package com.cavalcantgus.book_recommendation_system.entities;

import java.sql.Timestamp;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "\"user\"")
@Getter
@Setter
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	private Long id;

	private String username;
	private String email;
	private String password;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	@Setter(AccessLevel.NONE)
	private Set<Recommendation> recommendation;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	@Setter(AccessLevel.NONE)
	private Set<Rating> rating;

	/*
	 * Relacionamento bidirecional com Recommendation e Rating, onde um User pode
	 * estar associado a vários objetos Recommendatione e Rating
	 */

	@JsonIgnore
	@OneToOne(mappedBy = "user") // Relacionamento bidirecional com UserPreference de 1 para 1
	private UserPreferences userPreferences;

	@Setter(AccessLevel.NONE)
	private Timestamp createdAt;
	private Timestamp updatedAt;

	// Construtor parametrizado
	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Timestamp(System.currentTimeMillis());
		this.updatedAt = createdAt;
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Timestamp(System.currentTimeMillis());
	}

}
