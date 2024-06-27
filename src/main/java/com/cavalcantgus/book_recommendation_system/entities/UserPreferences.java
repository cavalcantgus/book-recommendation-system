package com.cavalcantgus.book_recommendation_system.entities;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "userpreferences")
public class UserPreferences {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "user_id", unique = true)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "genre_id")
	private Genre genre;
	private Double preferenceScore;
	private Timestamp created_at;
	
	public UserPreferences() {}
	
	public UserPreferences(User user, Genre genre, Double preferenceScore) {
		this.user = user;
		this.genre = genre;
		this.preferenceScore = preferenceScore;
		this.created_at = new Timestamp(System.currentTimeMillis());
	}
	
	public Long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public Double getPreferenceScore() {
		return preferenceScore;
	}

	public void setPreferenceScore(Double preferenceScore) {
		this.preferenceScore = preferenceScore;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	
}
