package com.cavalcantgus.book_recommendation_system.entities;

import java.sql.Timestamp;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String username;
	private String email;
	private String password;
	
	@OneToMany(mappedBy = "user")
	private Set<Recommendation> recommendation;
	
	@OneToMany(mappedBy = "user")
	private Set<Rating> rating;
	
	@OneToOne(mappedBy = "user")
	private UserPreferences userPreferences;
	private Timestamp created_at;
	private Timestamp updated_at;
	
	public User() {}
	
	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.created_at = new Timestamp(System.currentTimeMillis());
		this.updated_at = created_at;
	}

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
