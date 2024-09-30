package com.cavalcantgus.book_recommendation_system.entities;

import java.sql.Timestamp;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "genre")
@Getter
@Setter
@NoArgsConstructor
public class Genre {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(AccessLevel.NONE)
	private Long id;
	private String name;
	private String description;

	@JsonIgnore
	@OneToMany(mappedBy = "genre") // Relacionamento bidirecional com Book
	@Setter(AccessLevel.NONE)
	private Set<Book> book; // Um Book pode estar associado a somente um Genre
	private Timestamp createdAt;
	private Timestamp updatedAt;

	// Construtor parametrizado
	public Genre(String name, String description) {
		this.name = name;
		this.description = description;
		this.updatedAt = createdAt;
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
