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

@Entity // Mapeada como entidade JPA
@Table(name = "\"user\"")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Geração automática de ID 
	private Long id;
	
	private String username;
	private String email;
	private String password;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user") 
	private Set<Recommendation> recommendation;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private Set<Rating> rating;
	
	/*
	 * Relacionamento bidirecional com Recommendation e Rating, onde um User pode
	 * estar associado a vários objetos Recommendatione e Rating
	*/
	
	@JsonIgnore
	@OneToOne(mappedBy = "user") // Relacionamento bidirecional com UserPreference de 1 para 1
	private UserPreferences userPreferences;
	private Timestamp created_at;
	private Timestamp updated_at;
	
	// Construtor padrão
	public User() {}
	
	// Construtor parametrizado
	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}
	
	@PrePersist
	protected void onCreate() {
		this.created_at = new Timestamp(System.currentTimeMillis());
		this.updated_at = created_at;
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updated_at = new Timestamp(System.currentTimeMillis());
	}
	// Getters & Setters
	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Timestamp getCreatedAt() {
		return created_at;
	}
	
	public Timestamp getUpdatedAt() {
		return updated_at;
	}
	
	public void setUpdatedAt(Timestamp updated_at) {
		this.updated_at = updated_at;	
	}
	
	public Set<Rating> getRating(){
		return rating;
	}
	
	public Set<Recommendation> getRecommendation(){
		return recommendation;
	}
}
